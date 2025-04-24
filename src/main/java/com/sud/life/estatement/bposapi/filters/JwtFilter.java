package com.sud.life.estatement.bposapi.filters;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sud.life.estatement.exceptions.BposBusinessException;
import com.sud.life.estatement.exceptions.BposRuntimeException;
import com.sud.life.estatement.bposapi.dtos.*;
import com.sud.life.estatement.bposapi.services.BposApiServices;
import com.sud.life.estatement.bposapi.dtos.APIRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private BposApiServices bposApiServices;
    private static HttpStatus getHttpStatus(BposBusinessException exception) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (Objects.equals(exception.getMessageCode(),"MSG001") || Objects.equals(exception.getMessageCode(),"MSG002") || Objects.equals(exception.getMessageCode(), "MSG003") || Objects.equals(exception.getMessageCode(), "MSG006"))
            httpStatus = HttpStatus.UNAUTHORIZED;
        else if (Objects.equals(exception.getMessageCode(),"MSG004") || Objects.equals(exception.getMessageCode(), "MSG005"))
            httpStatus = HttpStatus.FORBIDDEN;
        return httpStatus;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = null;
        String userid = null;
        UserSessionInfoDTO externalSecurity=null;
        try {
            String refreshToken = request.getHeader("refreshToken");
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
                token = authorizationHeader.substring(7);
                externalSecurity= bposApiServices.externalSecurity(AuthResponse.builder().accessToken(token).refreshToken(refreshToken).build());
            }
            assert externalSecurity != null;
            if(externalSecurity.getStatus() != null && externalSecurity.getMessageCode()!=null){
                if("MSG011".equalsIgnoreCase(externalSecurity.getMessageCode()) ||"MSG006".equalsIgnoreCase(externalSecurity.getMessageCode()) || "MSG001".equalsIgnoreCase(externalSecurity.getMessageCode())){
                    throw new BposBusinessException((externalSecurity.getMessageCode()));
                }
                else{
                    throw new BposBusinessException(externalSecurity.getMessageCode());
                }
            }
            assert externalSecurity != null;
            UserSessionLog storedToken = externalSecurity.getUserSessionLog();
            if (storedToken.isExpired()) {
                SecurityContextHolder.clearContext();
                throw new BposBusinessException("MSG004");
            }
                userid = externalSecurity.getUserid();

            if (userid != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetailsDTO userDetails = externalSecurity.getUserDetails();
                if (Boolean.TRUE.equals(externalSecurity.getIsTokenValid())) {

                    ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                    for (AuthorityDTO authorityDTO :  userDetails.getAuthorities()) {
                        authorities.add(new SimpleGrantedAuthority(authorityDTO.getAuthority()));
                    }
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (BposBusinessException exception) {
            HttpStatus httpStatus = getHttpStatus(exception);
            response.setStatus(httpStatus.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Optional<BposMessageMasterDTO> messageDTO = bposApiServices.messages.stream()
                    .filter(data -> exception.getMessageCode().equals(data.getMsgCode()))
                    .findFirst();

            String messageValue = messageDTO.map(BposMessageMasterDTO::getMsgValue)
                    .orElse("Message not found");

            APIRequest<Void> apiRequest = APIRequest.<Void>builder()
                    .messageCode(exception.getMessageCode())
                    .message(messageValue)
                    .build();

            response.getWriter().write(convertObjectToJson(apiRequest));
            response.getWriter().flush(); // Ensure it's written
            return; // Important: prevents further processing
        }
        catch (Exception exception) {
            throw new BposRuntimeException(exception);
        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }


}
