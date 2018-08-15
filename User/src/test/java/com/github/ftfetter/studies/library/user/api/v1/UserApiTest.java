package com.github.ftfetter.studies.library.user.api.v1;

import com.github.ftfetter.studies.library.user.entity.User;
import com.github.ftfetter.studies.library.user.exception.ExpectationFailedException;
import com.github.ftfetter.studies.library.user.exception.InternalServerErrorException;
import com.github.ftfetter.studies.library.user.input.UserInput;
import com.github.ftfetter.studies.library.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserApiTest {
    
    private UserApi userApi;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userApi = new UserApi(userService);
    }

    @Test
    void getAllUsersTestException() throws Exception {
        when(userService.getAllUsers())
                .thenThrow(new Exception("exception test"));

        InternalServerErrorException thrown = assertThrows(
                InternalServerErrorException.class,
                () -> userApi.getAllUsers()
        );
        assertEquals("exception test", thrown.getMessage());
    }

    @Test
    void getUserByIdTestNoContent() throws Exception {
        when(userService.getUserById(anyString()))
                .thenReturn(buildEmpty());

        ResponseEntity response = userApi.getUserById("");
        
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getUserByIdTestException() throws Exception {
        when(userService.getUserById(anyString()))
                .thenThrow(new Exception("exception test"));

        InternalServerErrorException thrown = assertThrows(
                InternalServerErrorException.class,
                () -> userApi.getUserById("")
        );
        assertEquals("exception test", thrown.getMessage());
    }

    @Test
    void saveUserTestException() throws Exception {
        when(userService.saveUser(any(User.class)))
                .thenThrow(new Exception("exception test"));

        InternalServerErrorException thrown = assertThrows(
                InternalServerErrorException.class,
                () -> userApi.saveUser(buildUserInput())
        );
        assertEquals("exception test", thrown.getMessage());
    }

    @Test
    void alterUserTestExpectetionFailed() throws Exception {
        when(userService.updateUser(anyString(), any(UserInput.class)))
                .thenThrow(new ExpectationFailedException("exception test"));

        ExpectationFailedException thrown = assertThrows(
                ExpectationFailedException.class,
                () -> userApi.updateUser("", buildUserInput())
        );
        assertEquals("exception test", thrown.getMessage());
    }

    @Test
    void alterUserTestException() throws Exception {
        when(userService.updateUser(anyString(), any(UserInput.class)))
                .thenThrow(new Exception("exception test"));

        Exception thrown = assertThrows(
                Exception.class,
                () -> userApi.updateUser("", buildUserInput())
        );
        assertEquals("exception test", thrown.getMessage());
    }

    @Test
    void deleteUserTestNoContent() throws Exception {
        when(userService.deleteUser(anyString()))
                .thenReturn(buildUser());

        ResponseEntity response = userApi.deleteUser("");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteUserTestExpectationFailed() throws Exception {
        when(userService.deleteUser(anyString()))
                .thenThrow(new ExpectationFailedException("exception test"));

        ExpectationFailedException thrown = assertThrows(
                ExpectationFailedException.class,
                () -> userApi.deleteUser("")
        );
        assertEquals("exception test", thrown.getMessage());
    }

    @Test
    void deleteUserTestException() throws Exception {
        when(userService.deleteUser(anyString()))
                .thenThrow(new Exception("exception test"));

        Exception thrown = assertThrows(
                Exception.class,
                () -> userApi.deleteUser("")
        );
        assertEquals("exception test", thrown.getMessage());
    }

    private Optional<User> buildEmpty() {
        return Optional.empty();
    }

    private User buildUser() {
        return new User("", 1, LocalDate.now());
    }

    private UserInput buildUserInput() {
        return new UserInput();
    }
}