package com.github.ftfetter.studies.library.user.entity;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class User {

    @Id
    private String id;

    private String name;
    private Integer age;
    private LocalDate registerDate;

    public User(String name, Integer age, LocalDate registerDate) {
        this.name = name;
        this.age = age;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }
}
