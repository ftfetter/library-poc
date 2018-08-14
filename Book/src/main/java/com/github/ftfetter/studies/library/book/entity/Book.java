package com.github.ftfetter.studies.library.book.entity;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Book {

    @Id
    private String id;

    private String name;
    private String author;
    private Integer edition;
    private LocalDate registerDate;

    public Book(String name, String author, Integer edition, LocalDate registerDate) {
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.registerDate = registerDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }
}
