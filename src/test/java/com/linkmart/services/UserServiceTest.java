//package com.linkmart.services;
//import com.linkmart.dtos.UserDetailDto;
//import com.linkmart.repositories.UserAddressRepository;
//import com.linkmart.repositories.UserRepository;
//import com.linkmart.models.User;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.logging.Logger;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
// @SpringBootTest
//@Transactional
//public class UserServiceTest {
//
//    final Logger logger = Logger.getLogger(this.getClass().getName());
//
//    @Autowired
//     UserService userService;
//
//
//    @Test
//    public void testCreateUser() {
//        // String testEmail = "testingforCreateUser@gmail.com";
//        // String testPassword = "testPassword";
//        // User user = userService.createUser(testEmail, testPassword);
//        // System.out.println("User ID: " + user.getId());
//        // System.out.println("Username: " + user.getUsername());
//        // System.out.println("Email: " + user.getUserEmail());
//        // System.out.println("Password: " + user.getPassword());
//    }
//
////    @Test
////    public void testGetUserDetailById() {
////        String userId = "01HHMV7DKG4Z9JNT1P8DESHW8X"; // replace with a valid user ID
////        String expectedUsername = "kdl@gmail.com";
////        UserDetailDto userDetailDto = userService.getUserDetailById(userId);
////        logger.info("UserDetailDto: " + userDetailDto);
//////        assertNotNull(userDetailDto, "UserDetailDto should not be null");
//////        assertEquals(expectedUsername, userDetailDto.getUsername(), "User IDs should match");
////        // Add more assertions as needed...
////    }
//
//
//    @AfterEach
//    public  void cleanup() {
//        // userRepository.deleteByUserEmail("testingforCreateUser@gmail.com");
//    }
//
//}
