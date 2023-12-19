//package com.linkmart.repositories;
//
//import com.linkmart.repositories.OrdersRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.verify;
//
//@SpringBootTest
//public class OrdersRepositoryTest {
//
//    @Mock
//    private OrdersRepository ordersRepository;
//
//    @Test
//    public void testFindOrdersByUserId() {
//        // Arrange
//        String userId = "testUserId";
//
//        // Act
//        ordersRepository.findOrdersByUserId(userId);
//
//        // Assert
//        verify(ordersRepository).findOrdersByUserId(anyString());
//    }
//}