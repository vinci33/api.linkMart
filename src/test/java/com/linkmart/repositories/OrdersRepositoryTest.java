//package com.linkmart.repositories;
//
//import com.linkmart.dtos.OrdersByOrderIdImageDto;
//import com.linkmart.models.ImageModel;
//import com.linkmart.repositories.OrdersRepository;
//import com.linkmart.dtos.OrdersByOrderIdDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//public class OrdersRepositoryTest {
//
//    @Autowired
//    private OrdersRepository ordersRepository;
//
//    @Test
//    public void testFindImagesByOrderId() {
//        String orderId = "01HHZY2AREHH036SRJNY23N3QX"; // replace with an actual orderId
//        List<OrdersByOrderIdImageDto> results = ordersRepository.findImagesByOrderId(orderId);
//
//        // This will print the images to the console
//        System.out.println(results);
//
//        // You can also add assertions here to check if the result is as expected
//        // For example:
//        // assertNotNull(result.getImages());
//        // assertFalse(result.getImages().isEmpty());
//    }
//}