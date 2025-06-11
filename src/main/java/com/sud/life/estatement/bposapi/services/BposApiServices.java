package com.sud.life.estatement.bposapi.services;

import com.sud.life.estatement.bposapi.dtos.AuthResponse;
import com.sud.life.estatement.bposapi.dtos.BposConfigMasterDTO;
import com.sud.life.estatement.bposapi.dtos.BposMessageMasterDTO;
import com.sud.life.estatement.reports.DTO.AnnualUnitStatementRequestDTO;
import com.sud.life.estatement.reports.DTO.AnnualUnitStatementResponseDTO;
import com.sud.life.estatement.utils.BposUtils;
import com.sud.life.estatement.bposapi.dtos.UserSessionInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
@Service
public class BposApiServices {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${bpos.apis.userSessionsByJwt}")
    private String UserSessionJwt;

    @Value("${bpos.apis.messageCodes}")
    private String messageCodes;
    @Value("${bpos.apis.configValues}")
    private String configValues;


    public volatile List<BposMessageMasterDTO> messages = null;
    public volatile List<BposConfigMasterDTO> keys = null;

    public volatile AnnualUnitStatementRequestDTO requestDTO;

    public UserSessionInfoDTO externalSecurity(AuthResponse authResponse) {
        fetchAllMessageCodes();
        fetchAllConfigKeyValues();
        UserSessionInfoDTO userSessionInfo = null;
        String uri = UserSessionJwt;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuthResponse> requestEntity = new HttpEntity<>(authResponse, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, requestEntity, String.class);
        if (response.getBody() != null && response.getStatusCode().is2xxSuccessful()) {
            userSessionInfo = BposUtils.jsonAsObject(response.getBody(), UserSessionInfoDTO.class);
        }
        return userSessionInfo;
    }

    // messages = fetchAllMessageCodes();


    public void fetchAllMessageCodes() {
        if (messages == null) {
            synchronized (this) {
                if (messages == null) {
                    messages = new ArrayList<>();
                    String uri = messageCodes;
                    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
                    if (response.getBody() != null && response.getStatusCode().is2xxSuccessful()) {
                        messages = BposUtils.jsonAsList(response.getBody(), BposMessageMasterDTO.class);
                    }
                }
            }
        }
    }

    public void fetchAllConfigKeyValues() {
        if (keys == null) {
            synchronized (this) {
                if (keys == null) {
                    keys = new ArrayList<>();
                    String uri = configValues;
                    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
                    if (response.getBody() != null && response.getStatusCode().is2xxSuccessful()) {
                        keys = BposUtils.jsonAsList(response.getBody(), BposConfigMasterDTO.class);
                    }
                }
            }
        }
    }

    }


