package com.linkmart.controllers;

import com.linkmart.dtos.ResponseUserDto;
import com.linkmart.dtos.ResponseWithMessage;
import com.linkmart.dtos.UserWithProviderIdDto;
import com.linkmart.forms.UserInfoForm;
import com.linkmart.services.ProviderService;
import com.linkmart.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Tag(name = "User", description = """
        **User info APIs**

        - Represents the UserController, UserAddressController, UserPaymentMethodController class, which is responsible for handling various API endpoints related to users Info.

        - It interacts with the UserService, UserAddressService, UserPaymentMethodService and ProviderService classes to perform operations
        such as counting users, retrieving user information, updating user information, and getting a list of all users with their provider IDs.
                 
        """)
@RestController
@RequestMapping(value = "/api")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    private final ProviderService providerService;

    private final HttpServletRequest request;

    public UserController (UserService userService, ProviderService providerService, HttpServletRequest request){
        this.userService = userService;
        this.providerService = providerService;
        this.request = request;
    };



    @Operation(summary = "Get all user count",
            description = "Get the count of users",
            tags ={"User","Get"})
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

    @Operation(summary = "Get user info",
            description = "Get the current user's information",
            tags ={"User","Get"})
    @GetMapping("/user")
    public ResponseUserDto getUser() {
      try {
          var userId = (String)request.getAttribute("userId");
            var user = userService.getUserNameById(userId);
            var providerId = providerService.checkIfProvider(userId);
            return new ResponseUserDto(user, providerId );
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", e);
        }
    }

    @Operation(summary = "Get all user and provider id",
            description = "Get a list of all users with their provider IDs",
            tags ={"User","Get"})
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

    @Operation(summary = "Update user info",
            description = "Update the current user's information",
            tags ={"User","Put"})
    @PutMapping("/user/info")
    public ResponseWithMessage updateUser(@RequestBody UserInfoForm userInfoForm) {
      try {
          var userId = (String)request.getAttribute("userId");
          userService.updateUseInfo(userId, userInfoForm);
          return new ResponseWithMessage(true, "User updated");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}



