package com.linkmart.services;
import com.linkmart.repositories.UserRepository;
import com.linkmart.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
     UserService userService;

    @Autowired
     UserRepository userRepository;
    @Test
    public void testCreateUser() {
        String testEmail = "testingforCreateUser@gmail.com";
        String testPassword = "testPassword";
        User user = userService.createUser(testEmail, testPassword);
        System.out.println("User ID: " + user.getId());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getUserEmail());
        System.out.println("Password: " + user.getPassword());
    }


    @AfterEach
    public  void cleanup() {
        userRepository.deleteByUserEmail("testingforCreateUser@gmail.com");
    }

}
