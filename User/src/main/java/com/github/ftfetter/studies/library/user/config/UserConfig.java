package com.github.ftfetter.studies.library.user.config;

import com.github.ftfetter.studies.library.user.api.v1.UserApi;
import com.github.ftfetter.studies.library.user.repository.UserRepository;
import com.github.ftfetter.studies.library.user.service.UserService;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class UserConfig {

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient("localhost");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(),"users");
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepository(mongoTemplate());
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserApi userApi() {
        return new UserApi(userService());
    }
}
