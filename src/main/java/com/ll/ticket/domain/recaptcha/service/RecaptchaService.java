package com.ll.ticket.domain.recaptcha.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ll.ticket.global.app.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RecaptchaService {
    public boolean verifyRecaptcha(String recaptcha) {
        String secretKey = AppConfig.getRecaptchaSecretKey();
        String url = AppConfig.getRecaptchaUrl();

        try {
            HttpHeaders httpHeaders= new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("secret", secretKey);
            map.add("response", recaptcha);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();
            return jsonObject.get("success").getAsBoolean();
        } catch (Exception e) {
            return false;
        }
    }
}
