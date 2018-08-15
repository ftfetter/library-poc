package com.github.ftfetter.studies.library.user.entity.mapper;

import com.github.ftfetter.studies.library.user.entity.User;
import com.github.ftfetter.studies.library.user.input.UserInput;

import java.time.LocalDate;

public class UserMapper {

    public static User map(UserInput input) {
        return new User(input.getName(), input.getAge(), LocalDate.now());
    }

    public static User merge(User entity, UserInput input) {
        entity.setName(input.getName());
        entity.setAge(input.getAge());
        return entity;
    }
}
