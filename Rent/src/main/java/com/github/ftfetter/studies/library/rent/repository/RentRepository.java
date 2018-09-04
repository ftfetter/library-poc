package com.github.ftfetter.studies.library.rent.repository;

import com.github.ftfetter.studies.library.rent.entity.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RentRepository {

    private MongoTemplate mongoTemplate;

    @Autowired
    public RentRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Rent insert(Rent rent) {
        mongoTemplate.insert(rent);
        return rent;
    }
}
