//package com.linkmart.services;
//
//
//
//import com.linkmart.models.Offer;
//import com.linkmart.models.Orders;
//import com.linkmart.repositories.OfferRepository;
//import com.linkmart.repositories.OrdersRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class OrdersServiceTest {
//
//    @Mock
//    RequestService requestService;
//
//    @Mock
//    OfferRepository offerRepository;
//
//    @Mock
//    OfferService offerService;
//
//    @Mock
//    OrdersRepository ordersRepository;
//
//    @InjectMocks
//    OrdersService ordersService;
//
//    @Test
//    public void testCreateOrderStatusUpdates() {
//        // Arrange
//        String userId = "testUserId";
//        String offerId = "testOfferId";
//        String requestId = "testRequestId";
//        Integer userAddressId = 1;
//
//        Offer offer = new Offer();
//        offer.setOfferId(offerId);
//        offer.setRequestId(requestId);
//        offerService.setStatusOpen(offer); // Open status
//
//        when(offerRepository.findOfferByOfferId(offerId)).thenReturn(offer);
//        when(offerService.getOfferByRequestIdAndOfferStatusId(requestId, 6)).thenReturn(Collections.singletonList(offer));
//
//        // Act
//        String result = ordersService.createOrder(true, userId, offerId, userAddressId);
//
//        // Assert
//        verify(requestService, times(1)).updateRequestStatus(requestId, true, false); // Verify if updateRequestStatus is called with correct parameters
//        verify(offerService, times(1)).setStatusPending(offer); // Verify if setStatusPending is called with correct parameters
//
//        assertEquals(1, offer.getOfferStatusId()); // Check if offer status is updated to Pending
//        assertEquals("Created", ordersService.getStatusCreate(new Orders())); // Check if order status is Created
//
//        // Print out the statuses
//        System.out.println("Offer Status: " + offer.getOfferStatusId());
//        System.out.println("Order Status: " + ordersService.getStatusCreate(new Orders()));
//        // Print out the request status here
//    }
//}
