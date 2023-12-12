package com.linkmart.controllers;
import com.linkmart.dtos.ResponseWithToken;
import com.linkmart.forms.LoginForm;
import com.linkmart.services.UserService;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());


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
            var user = userService.createUser(loginForm.getEmail(), loginForm.getPassword());
            return new ResponseWithToken(true, "Signup success", user.getId());
        }catch(Exception e){
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    e.getMessage(),e);
        }
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World  deploy from github actions";
    }

}
