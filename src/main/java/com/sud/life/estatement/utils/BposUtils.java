package com.sud.life.estatement.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
@Slf4j
public class BposUtils {

    public static String getBposLoginUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return "";
    }

    public static Object getTypeCastedValue(String key, Object object) {
        switch (object.getClass().getSimpleName()) {
            case "Long":
            case "Integer":
                return Long.valueOf(object.toString());
            default:
                return String.valueOf(object);
        }
    }

    public static String jsonAsString(Object obj) {
        log.info("BposUtils:jsonAsString execution started.");
        String jsonString = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
            jsonString = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException exception) {
            log.error("Exception occurred while converting object as json string. Exception message " + exception.getMessage());
            throw new RuntimeException("Exception occurred while converting object as json string. Exception message " + exception.getMessage());
        }
        log.info("BposUtils:jsonAsString execution ended.");
        return jsonString;
    }

    public <T> T jsonAsObject(String jsonString, Class<T> objectClass) {
        log.info("BposUtils:jsonAsString execution started.");
        try {
            ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(jsonString, objectClass);
        } catch (JsonProcessingException exception) {
            log.error("Exception occurred while converting json as object. Exception message " + exception.getMessage());
            throw new RuntimeException("Exception occurred while converting json as object. Exception message " + exception.getMessage());
        }
    }

    public <T> List<T> jsonAsList(String jsonString, Class<T> objectClass) {
        log.info("BposUtils:jsonAsString execution started.");
        try {
            ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, objectClass);
            return objectMapper.readValue(jsonString, listType);
        } catch (JsonProcessingException exception) {
            log.error("Exception occurred while converting object as json List. Exception message " + exception.getMessage());
            throw new RuntimeException("Exception occurred while converting object as json List. Exception message " + exception.getMessage());
        }
    }
}