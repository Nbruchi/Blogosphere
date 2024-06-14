package com.blogosphere.backend.services;

import com.blogosphere.backend.models.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ClerkService {
    @Value("${clerk.api.key}")
    private String clerkApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean validateToken(String token) {
        String url = "https://api.clerk.dev/v1/tokens/verify";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + clerkApiKey);
        headers.set("Content-Type", "application/json");

        String body = "{\"token\": \"" + token + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getStatusCode() == HttpStatus.OK;
    }

    public UserInfo getUserInfo(String token) {
        String url = "https://api.clerk.dev/v1/users";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + clerkApiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("token", token);

        ResponseEntity<UserInfo> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, UserInfo.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to retrieve user info");
        }
    }
}
