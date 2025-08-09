package com.example.calculator.service;

import com.example.calculator.model.User;
import com.example.calculator.exception.UserNotFoundException;
import com.example.calculator.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User updateUserData) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updateUserData.getFirstName());
                    existingUser.setLastName(updateUserData.getLastName());
                    existingUser.setEmail(updateUserData.getEmail());


                    existingUser.setPassword(passwordEncoder.encode(updateUserData.getPassword()));

                    existingUser.setAge(updateUserData.getAge());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}

