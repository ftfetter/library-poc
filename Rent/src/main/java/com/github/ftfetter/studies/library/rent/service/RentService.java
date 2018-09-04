package com.github.ftfetter.studies.library.rent.service;

import com.github.ftfetter.studies.library.rent.client.book.BookClient;
import com.github.ftfetter.studies.library.rent.client.user.UserClient;
import com.github.ftfetter.studies.library.rent.entity.Rent;
import com.github.ftfetter.studies.library.rent.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class RentService {

    private final Integer MAX_BOOKS_PER_USER = 3;
    private final Integer DAYS_UNTIL_EXPIRATION = 7;

    private BookClient bookClient;

    private UserClient userClient;

    private RentRepository rentRepository;

    @Autowired
    public RentService(BookClient bookClient, UserClient userClient, RentRepository rentRepository) {
        this.bookClient = bookClient;
        this.userClient = userClient;
        this.rentRepository = rentRepository;
    }

    public Rent rent(String userId, String bookId) {

        LocalDate registerDate = LocalDate.now();
        LocalDate expirationDate = registerDate.plusDays(DAYS_UNTIL_EXPIRATION);

        Rent actualRent = new Rent(userId, bookId, registerDate, expirationDate);

        return rentRepository.insert(actualRent);
    }
}
