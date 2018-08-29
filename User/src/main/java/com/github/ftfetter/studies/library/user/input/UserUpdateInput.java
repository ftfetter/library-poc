package com.github.ftfetter.studies.library.user.input;

public class UserUpdateInput {

    private String name;
    private Integer age;
    private String situation;

    public UserUpdateInput() {
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

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}
