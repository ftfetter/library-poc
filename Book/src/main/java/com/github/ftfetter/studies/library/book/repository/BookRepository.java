package com.github.ftfetter.studies.library.book.repository;

import com.github.ftfetter.studies.library.book.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Optional;

public class BookRepository {

    private MongoTemplate mongoTemplate;

    @Autowired
    public BookRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Book> findAll() throws Exception {
        try {
            return mongoTemplate.findAll(Book.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Problemas para buscar os dados no banco.", e);
        }
    }

    public Optional<Book> findById(String id) throws Exception {
        try {
            return Optional.ofNullable(mongoTemplate.findById(id, Book.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Problemas para buscar os dados no banco.", e);
        }
    }

    public Book insert(Book book) throws Exception {
        try {
            mongoTemplate.insert(book);
            return book;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Problemas na inserção dos dados no banco.", e);
        }
    }

    public Book update(Book book) throws Exception {
        try {
            mongoTemplate.save(book);
            return book;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Problema na alteração dos dados no banco.", e);
        }
    }

    public Book delete(Book book) throws Exception {
        try {
            mongoTemplate.remove(book);
            return book;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Problema na exclusão dos dados no banco.", e);
        }
    }
}
