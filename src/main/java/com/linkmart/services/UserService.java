package com.linkmart.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.linkmart.dtos.UserWithProviderIdDto;
import com.linkmart.models.User;
import com.linkmart.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";



    @Autowired
     UserRepository userRepository;

    @Autowired
    Environment env;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long countUser() {
        return   userRepository.count();
    }

    public void validateUserEmail(String email) {
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

    public void validateUserId(String userId) {
        var userByUserId = userRepository.findUserById(userId);
        if (userByUserId == null ) {
            throw new IllegalArgumentException("Invalid UserId ");
        }
    }

    public String createUser(String email, String password) {
        try {
            validateUserEmail(email);
            validateUsername(email);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw e;
        }
        var user = new User();
        user.setUserEmail(email);
        user.setUsername(email); // Default username will be email;
        user.setPassword(BCrypt.withDefaults().hashToString(10, password.toCharArray()));
        var authUser = userRepository.saveAndFlush(user);
        return JWT.create()
                .withIssuer("admin")
                .withClaim("userId", authUser.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + 10000*1000000000))
                .sign(Algorithm.HMAC256(Objects.requireNonNull(env.getProperty("jwt.secret"))));

    }

    public User createUserWithRandom(String email,String Username, String password) {
        validateUserEmail(email);
        validateUsername(Username);
        var user = new User();
        user.setUserEmail(email);
        user.setUsername(Username); // Random username ;
        user.setPassword(BCrypt.withDefaults().hashToString(10, password.toCharArray()));
        return userRepository.saveAndFlush(user);
    }

    public String authenticateUser(String email, String password) throws Exception {
        var users = userRepository.findUserByUserEmail(email);
        Date expireDate = new Date(new Date().getTime() + 10000*1000000000);
        System.out.println(expireDate);
        logger.info(expireDate.toString());
        if (users.isEmpty()) {
            throw new Exception("Wrong email or password");
        }
        var user = users.get(0);
        var result = BCrypt.verifyer().verify(password.getBytes(), user.getPassword().getBytes());
        if (!result.verified) {
            throw new Exception("Wrong email or password");
        }
        return JWT.create()
                .withIssuer("admin")
                .withClaim("userId", user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(Objects.requireNonNull(env.getProperty("jwt.secret"))));
    }

    public String getUserNameById(String userId) {
        validateUserId(userId);
        var user = userRepository.findUserById(userId);
        return user.getUsername();
    }

    public List<UserWithProviderIdDto> getAllUser() {
          var userWithProviderId = userRepository.getAllUserWithProviderId();
        return (List<UserWithProviderIdDto>) userWithProviderId;
    }
}
