package com.github.ftfetter.studies.library.rent.client.book;

import com.github.ftfetter.studies.library.rent.client.book.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class BookClient {

    private final String baseURL = "localhost:8081";

    private RestTemplate restTemplate;

    @Autowired
    public BookClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BookResponse getBookById(String userId) throws Exception {
        URI uri = new URI(String.format("%s/v1/books/%s", baseURL, userId));
        return restTemplate.getForObject(uri, BookResponse.class);
    }
}
