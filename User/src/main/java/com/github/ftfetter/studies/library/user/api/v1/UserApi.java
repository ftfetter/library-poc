package com.github.ftfetter.studies.library.user.api.v1;

import com.github.ftfetter.studies.library.user.entity.mapper.UserMapper;
import com.github.ftfetter.studies.library.user.exception.InternalServerErrorException;
import com.github.ftfetter.studies.library.user.input.UserRegistrationInput;
import com.github.ftfetter.studies.library.user.input.UserUpdateInput;
import com.github.ftfetter.studies.library.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/users")
public class UserApi {

    private UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id) {
        try {
            return userService.getUserById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserRegistrationInput input) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(UserMapper.map(input)));
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody UserUpdateInput input) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, input));
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
