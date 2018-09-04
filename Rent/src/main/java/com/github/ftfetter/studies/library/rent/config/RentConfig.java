package com.github.ftfetter.studies.library.rent.config;

import com.github.ftfetter.studies.library.rent.api.v1.RentApi;
import com.github.ftfetter.studies.library.rent.client.book.BookClient;
import com.github.ftfetter.studies.library.rent.client.user.UserClient;
import com.github.ftfetter.studies.library.rent.repository.RentRepository;
import com.github.ftfetter.studies.library.rent.service.RentService;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RentConfig {

    @Bean
    private MongoClient mongoClient() {
        return new MongoClient("localhost");
    }

    @Bean
    private MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "rents");
    }

    @Bean
    private RentRepository rentRepository() {
        return new RentRepository(mongoTemplate());
    }

    @Bean
    private RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    private BookClient bookClient() {
        return new BookClient(restTemplate());
    }

    @Bean
    private UserClient userClient() {
        return new UserClient(restTemplate());
    }

    @Bean
    private RentService rentService() {
        return new RentService(bookClient(), userClient(), rentRepository());
    }

    @Bean
    private RentApi rentApi() {
        return new RentApi(rentService());
    }
}
