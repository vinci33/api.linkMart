package com.linkmart.controllers;
import com.linkmart.dtos.ResponseWithToken;
import com.linkmart.forms.LoginForm;
import com.linkmart.models.RandomGenModel;
import com.linkmart.service.UserService;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@RestController
public class AuthController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Autowired
    UserService userService;

    @Autowired
    Environment env;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseWithToken login ( @RequestBody LoginForm loginForm){
        try{
            var jwt = userService.authenticateUser(loginForm.getEmail(), loginForm.getPassword());
            return new ResponseWithToken (true, "Login success", jwt);

        }catch(Exception e){
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    e.getMessage(),e);
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseWithToken signup (@RequestBody LoginForm loginForm){
        try{
            var username = generateRandomString(10);
            if (!loginForm.getUsername().isEmpty()) {
                username = loginForm.getUsername();
            }
            var user = userService.createUser(loginForm.getEmail(), username, loginForm.getPassword());
            return new ResponseWithToken(true, "Signup success", user.getId());
        }catch(Exception e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    e.getMessage(),e);
        }
    }

    private static String generateRandomString(int length) {
        // Create an instance of Random
        Random random = new Random();

        // Create a StringBuilder to store the random string
        StringBuilder stringBuilder = new StringBuilder(length);

        // Generate random characters and append them to the StringBuilder
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        // Get the final random string
        return stringBuilder.toString();
    }
}
