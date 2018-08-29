package com.github.ftfetter.studies.library.user.entity.mapper;

import com.github.ftfetter.studies.library.user.entity.User;
import com.github.ftfetter.studies.library.user.input.UserRegistrationInput;
import com.github.ftfetter.studies.library.user.input.UserUpdateInput;
import com.github.ftfetter.studies.library.user.type.UserSituation;
import com.github.ftfetter.studies.library.user.util.StringUtils;

import java.time.LocalDate;
import java.util.Optional;

public class UserMapper {

    public static User map(UserRegistrationInput input) {
        return new User(input.getName(), input.getAge(), LocalDate.now(), UserSituation.ABLE);
    }

    public static User merge(User entity, UserUpdateInput input) {
        entity.setName(StringUtils.notBlankOrElse(input.getName(), entity.getName()));
        entity.setAge(Optional.ofNullable(input.getAge()).orElse(entity.getAge()));
        entity.setSituation(UserSituation.get(input.getSituation()).orElse(entity.getSituation()));
        return entity;
    }
}
