package com.github.ftfetter.studies.library.user.service;

import com.github.ftfetter.studies.library.user.entity.User;
import com.github.ftfetter.studies.library.user.exception.ExpectationFailedException;
import com.github.ftfetter.studies.library.user.input.UserInput;
import com.github.ftfetter.studies.library.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void getAllUsersTest() throws Exception {
        List<User> usersFound = buildUsers();

        when(userRepository.findAll())
                .thenReturn(usersFound);

        assertIterableEquals(usersFound, userService.getAllUsers());
    }

    @Test
    void getUserByIdTestUserFound() throws Exception {
        Optional<User> userFound = buildUserFound();

        when(userRepository.findById(anyString()))
                .thenReturn(userFound);

        assertEquals(userFound, userService.getUserById(""));
    }

    @Test
    void getUserByIdTestUserNotFound() throws Exception {
        Optional<User> userNotFound = buildEmpty();

        when(userRepository.findById(anyString()))
                .thenReturn(userNotFound);

        assertEquals(userNotFound, userService.getUserById(""));
    }

    @Test
    void saveUserTest() throws Exception {
        User userSaved = buildUser();

        when(userRepository.insert(any(User.class)))
                .thenReturn(userSaved);

        assertEquals(userSaved, userService.saveUser(buildUser()));
    }

    @Test
    void updateUserTest() throws Exception {
        User userUpdated = buildUser();

        when(userRepository.findById(anyString()))
                .thenReturn(buildUserFound());
        when(userRepository.update(any(User.class)))
                .thenReturn(userUpdated);

        assertEquals(userUpdated, userService.updateUser("", buildUserInput()));
    }

    @Test
    void updateUserTestNotFoud() throws Exception {
        when(userRepository.findById(anyString()))
                .thenReturn(buildEmpty());

        ExpectationFailedException thrown = assertThrows(
                ExpectationFailedException.class,
                () -> userService.updateUser("", buildUserInput())
        );
        assertEquals("Nenhum usuário foi encontrado para alteração.", thrown.getMessage());
    }

    @Test
    void deleteUserTest() throws Exception {
        User userDeleted = buildUser();

        when(userRepository.findById(anyString()))
                .thenReturn(buildUserFound());
        when(userRepository.delete(any(User.class)))
                .thenReturn(userDeleted);

        assertEquals(userDeleted, userService.deleteUser(""));
    }

    @Test
    void deleteUserTestNotFoud() throws Exception {
        when(userRepository.findById(anyString()))
                .thenReturn(buildEmpty());

        ExpectationFailedException thrown = assertThrows(
                ExpectationFailedException.class,
                () -> userService.deleteUser("")
        );
        assertEquals("Nenhum usuário foi encontrado para remoção.", thrown.getMessage());
    }

    private Optional<User> buildEmpty() {
        return Optional.empty();
    }

    private Optional<User> buildUserFound() {
        return Optional.of(buildUser());
    }

    private List<User> buildUsers() {
        return Arrays.asList(buildUser());
    }

    private User buildUser() {
        return new User("name", 1, LocalDate.now());
    }

    private UserInput buildUserInput() {
        return new UserInput();
    }
}