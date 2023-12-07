package com.linkmart.controllers;

import com.linkmart.repositories.UserRepository;
import com.linkmart.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/user")
    public String postUser() {
        return "success";
    }
}
