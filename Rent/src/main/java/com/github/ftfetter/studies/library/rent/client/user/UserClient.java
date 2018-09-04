package com.github.ftfetter.studies.library.rent.client.user;

import com.github.ftfetter.studies.library.rent.client.user.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class UserClient {

    private final String baseURL = "localhost:8082";

    private RestTemplate restTemplate;

    @Autowired
    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserResponse getUserById(String userId) throws Exception {
        URI uri = new URI(String.format("%s/v1/users/%s", baseURL, userId));
        return restTemplate.getForObject(uri, UserResponse.class);
    }
}
