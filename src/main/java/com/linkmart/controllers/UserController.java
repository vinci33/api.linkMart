package com.linkmart.controllers;

import com.linkmart.dtos.ResponseWithMessage;
import com.linkmart.repositories.UserRepository;
import com.linkmart.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    UserService userService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/admin/user/count")
    public ResponseWithMessage countUser() {
      try {
          Long userCount = userService.countUser();
          return new ResponseWithMessage(true, "User count: " + userCount);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", e);
        }

    }


}

//    @GetMapping("/user/details/")


