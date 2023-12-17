package com.linkmart.controllers;

import com.linkmart.dtos.ResponseUserSto;
import com.linkmart.dtos.ResponseWithMessage;
import com.linkmart.dtos.UserWithProviderIdDto;
import com.linkmart.services.ProviderService;
import com.linkmart.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ProviderService providerService;
    @Autowired
    HttpServletRequest request;

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

    @GetMapping("/user")
    public ResponseUserSto getUser() {
      try {
          var userId = (String)request.getAttribute("userId");
          logger.info("userId: " + userId);
            var user = userService.getUserNameById(userId);
            logger.info("user: " + user);
            var providerId = providerService.checkIfProvider(userId);
            logger.info("providerId: " + providerId);
            return new ResponseUserSto(user, providerId );
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", e);
        }

    }

    @GetMapping("/user/userAndProvider")
    public List<UserWithProviderIdDto> getAllUser() {
      try {
          List<UserWithProviderIdDto> userWithProviderId = userService.getAllUser();
            return  userWithProviderId;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", e);
        }

    }




}



