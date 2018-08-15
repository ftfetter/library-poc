package com.github.ftfetter.studies.library.user.service;

import com.github.ftfetter.studies.library.user.entity.User;
import com.github.ftfetter.studies.library.user.entity.mapper.UserMapper;
import com.github.ftfetter.studies.library.user.exception.ExpectationFailedException;
import com.github.ftfetter.studies.library.user.input.UserInput;
import com.github.ftfetter.studies.library.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() throws Exception {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) throws Exception {
        return userRepository.findById(id);
    }

    public User saveUser(User user) throws Exception {
        return userRepository.insert(user);
    }

    public User updateUser(String id, UserInput input) throws Exception {
        User updatedUser = userRepository.findById(id)
                .map(user -> UserMapper.merge(user, input))
                .orElseThrow(() -> new ExpectationFailedException("Nenhum usuário foi encontrado para alteração."));
        return userRepository.update(updatedUser);
    }

    public User deleteUser(String id) throws Exception {
        User userFound = userRepository.findById(id)
                .orElseThrow(() -> new ExpectationFailedException("Nenhum usuário foi encontrado para remoção."));
        return userRepository.delete(userFound);
    }
}
