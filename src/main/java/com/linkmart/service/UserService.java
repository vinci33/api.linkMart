package com.linkmart.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.linkmart.models.User;
import com.linkmart.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service

public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    Environment env;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long countUser() {
        return   userRepository.count();
    }

    public void validateUserEmail(String email, String username) {
        var usersByEmail = userRepository.findUserByUserEmail(email);
        if (!usersByEmail.isEmpty()) {
            throw new IllegalArgumentException("Email already exists");
        }
    }
    public void validateUsername(String username) {
        var usersByUsername = userRepository.findUserByUsername(username);
        if (!usersByUsername.isEmpty()) {
            throw new IllegalArgumentException("Username already exists");
        }
    }

    public User createUser(String email, String username, String password) {
        validateUserEmail(email, username);
        validateUsername(username);
        var user = new User();
        user.setUserEmail(email);
        user.setUsername(username);
        user.setPassword(BCrypt.withDefaults().hashToString(10, password.toCharArray()));
        return userRepository.saveAndFlush(user);
    }

    public String authenticateUser(String email, String password) {
        var users = userRepository.findUserByUserEmail(email);
        Date expireDate = new Date(new Date().getTime() + 10000*1000000000);
        System.out.println(expireDate);
        logger.info(expireDate.toString());
        if (users.isEmpty()) {
            throw new Error("Missing username or password");
        }
        var user = users.get(0);
        var result = BCrypt.verifyer().verify(password.getBytes(), user.getPassword().getBytes());
        if (!result.verified) {
            throw new Error("Missing username or password");
        }
        return JWT.create()
                .withIssuer("admin")
                .withClaim("userId", user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(Objects.requireNonNull(env.getProperty("jwt.secret"))));
    }
}
