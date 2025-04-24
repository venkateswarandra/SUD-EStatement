package com.sud.life.estatement.bposapi.services;

import com.sud.life.estatement.bposapi.dtos.AuthResponse;
import com.sud.life.estatement.bposapi.dtos.BposMessageMasterDTO;
import com.sud.life.estatement.utils.BposUtils;
import com.sud.life.estatement.bposapi.dtos.UserSessionInfoDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class BposApiServices {
    @Autowired
    private RestTemplate restTemplate;
    public List<BposMessageMasterDTO> messages=null;
    public UserSessionInfoDTO externalSecurity(AuthResponse authResponse){
        UserSessionInfoDTO userSessionInfo=null;
        String uri="http://192.168.2.24:8080/bpos2.0/sud/bpos/getUserSessionInfoByJWT";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuthResponse> requestEntity = new HttpEntity<>(authResponse, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, requestEntity, String.class);
        if (response.getBody() != null && response.getStatusCode().is2xxSuccessful()) {
            userSessionInfo = BposUtils.jsonAsObject(response.getBody(), UserSessionInfoDTO.class);
        }
        return userSessionInfo;
    }
    @PostConstruct
    public void init() {
        messages = fetchAllMessageCodes();

    }

    public List<BposMessageMasterDTO> fetchAllMessageCodes() {
        List<BposMessageMasterDTO> messageList = new ArrayList<>();
        String uri = "http://192.168.2.24:8080/bpos2.0/sud/bpos/getAllMessageCode";
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        if (response.getBody() != null && response.getStatusCode().is2xxSuccessful()) {
            messageList = BposUtils.jsonAsList(response.getBody(), BposMessageMasterDTO.class);
        }
        return messageList;
    }

}
