package com.github.ftfetter.studies.library.book.config;

import com.github.ftfetter.studies.library.book.api.v1.BookApi;
import com.github.ftfetter.studies.library.book.repository.BookRepository;
import com.github.ftfetter.studies.library.book.service.BookService;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class BookConfig {

    @Bean
    public MongoClient mongo() {
        return new MongoClient("localhost");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), "books");
    }

    @Bean
    public BookRepository bookRepository() {
        return new BookRepository(mongoTemplate());
    }

    @Bean
    public BookService bookService() {
        return new BookService(bookRepository());
    }

    @Bean
    public BookApi bookApi() {
        return new BookApi(bookService());
    }
}
