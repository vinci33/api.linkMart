//package com.linkmart.controllers;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.linkmart.dtos.UserAddressDto;
//import com.linkmart.service.UserAddressService;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import java.util.Arrays;
//import java.util.List;
//
//@WebMvcTest(UserAddressController.class)
//public class UserAddressControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserAddressService userAddressService;
//
//    @MockBean
//    private HttpServletRequest request;
//
//    @Test
//    public void testGetUserAddress() throws Exception {
//        // Arrange
//        String userId = "testUserId";
//        when(request.getAttribute("userId")).thenReturn(userId);
//
//
//        UserAddressDto dto = new UserAddressDto("testAddress", true);
//        List<UserAddressDto> dtos = Arrays.asList(dto);
//        when(userAddressService.findUserAddressByUserId(userId)).thenReturn(dtos);
//
//        // Act and Assert
//        mockMvc.perform(get("/api/user/address"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{\"address\":\"testAddress\",\"isPrimary\":true}]"));
//    }
//}