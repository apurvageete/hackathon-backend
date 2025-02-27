package com.summary.codeGist.Service;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ChatContentService {
    private final RestTemplate restTemplate = new RestTemplate();
    //
    private final String EXTERNAL_API_URL = "https://external-api.com/endpoint";

    public ResponseEntity<String> processFormData(Map<String, String> formData, String token) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.setAll(formData);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(EXTERNAL_API_URL, HttpMethod.POST, requestEntity, String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
