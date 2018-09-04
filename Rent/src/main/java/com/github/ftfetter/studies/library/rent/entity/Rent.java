package com.github.ftfetter.studies.library.rent.entity;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Rent {

    @Id
    private String id;

    private String userId;
    private String bookId;
    private LocalDate registerDate;
    private LocalDate expirationDate;
    private LocalDate returnDate;

    public Rent(String userId, String bookId, LocalDate registerDate, LocalDate expirationDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.registerDate = registerDate;
        this.expirationDate = expirationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
