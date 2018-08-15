package com.github.ftfetter.studies.library.user.repository;

import com.github.ftfetter.studies.library.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Optional;

public class UserRepository {

    private MongoTemplate mongoTemplate;

    @Autowired
    public UserRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<User> findAll() throws Exception {
        try {
            return mongoTemplate.findAll(User.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Problemas para buscar os dados no banco.", e);
        }
    }

    public Optional<User> findById(String id) throws Exception {
        try {
            return Optional.ofNullable(mongoTemplate.findById(id, User.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Problemas para buscar os dados no banco.", e);
        }
    }

    public User insert(User user) throws Exception {
        try {
            mongoTemplate.insert(user);
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Problemas na inserção dos dados no banco.", e);
        }
    }

    public User update(User user) throws Exception {
        try {
            mongoTemplate.save(user);
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Problemas na alteração dos dados no banco.", e);
        }
    }

    public User delete(User user) throws Exception {
        try {
            mongoTemplate.remove(user);
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Problemas na exclusão dos dados no banco.", e);
        }
    }
}
