package com.linkmart.controllers;
import com.linkmart.dtos.ResponseWithToken;
import com.linkmart.forms.LoginForm;
import com.linkmart.models.RandomGenModel;
import com.linkmart.models.RandomGenModel;
import com.linkmart.services.UserService;
import com.linkmart.utils.UtilMethod;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;


@Tag(name = "Authenticate" , description = """
        **Auth APIs**
        
        - Represents the AuthController class, which handles authentication and signup requests.
        
        - It uses the UserService class to perform user-authenticated operations for authenticating a user and creating a new user.
        """)
@RestController
public class AuthController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    Environment env;

    @Operation(summary = "Login",
            description = """
                    Handles the login request by authenticating the user's email and password.
                    If the authentication is successful, it returns a ResponseWithToken object containing a success message and a JWT token
                    """,
            tags ={"Authenticate","Post"})
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseWithToken login ( @RequestBody LoginForm loginForm){
        try{
            var jwt = userService.authenticateUser(loginForm.getEmail(), loginForm.getPassword());
            return new ResponseWithToken ( "Login success", jwt);
        }catch(Exception e){
            logger.error("test");
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    e.getMessage(),e);
        }
    }

    @Operation(summary = "Signup",
            description = """
                    Handles the signup request by creating a new user with the given email and password.
                    Default username is the provided email.
                    If the signup is successful, it returns a ResponseWithToken object containing a success message and a JWT token
                    """,
            tags ={"Authenticate","Post"})
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseWithToken signup (@RequestBody LoginForm loginForm){
        try{
            var username = UtilMethod.generateRandomString(10);

            var userWthJwt = userService.createUser(loginForm.getEmail(),  loginForm.getPassword());
            return new ResponseWithToken("Signup success", userWthJwt);
        }catch(Exception e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    e.getMessage(),e);
        }
    }



//    @GetMapping("/")
//    public String hello() {
//        return "https://api.fight2gether.com/swagger-ui/index.html";
//    }

}
