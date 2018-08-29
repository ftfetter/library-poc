package com.github.ftfetter.studies.library.user.type;

import java.util.Optional;
import java.util.stream.Stream;

public enum UserSituation {

    ABLE    ("ABLE"),
    FULL    ("FULL"),
    IN_DEBT ("IN DEBT");

    private String code;

    UserSituation(String code) {
        this.code = code;
    }

    public static Optional<UserSituation> get(String code) {
        return Stream.of(values())
                .filter(situation -> situation.code.equalsIgnoreCase(code))
                .findFirst();
    }
}
