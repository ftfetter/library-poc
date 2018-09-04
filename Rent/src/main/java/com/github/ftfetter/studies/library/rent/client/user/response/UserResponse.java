package com.github.ftfetter.studies.library.rent.client.user.response;

import com.github.ftfetter.studies.library.rent.type.UserSituation;

import java.time.LocalDate;

public class UserResponse {

    private String id;
    private String name;
    private Integer age;
    private LocalDate registerDate;
    private UserSituation situation;

    public UserResponse() {
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

    public UserSituation getSituation() {
        return situation;
    }

    public void setSituation(UserSituation situation) {
        this.situation = situation;
    }
}
