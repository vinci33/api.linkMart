package com.linkmart.services;

import com.linkmart.dtos.*;
import com.linkmart.forms.AcceptOfferForm;
import com.linkmart.models.Offer;
import com.linkmart.models.RequestModel;
import com.linkmart.repositories.OfferRepository;
import com.linkmart.repositories.RequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {

    @Mock
    UserService userService;

    @Mock
    UserAddressService userAddressService;

    @Mock
    OfferRepository offerRepository;

    @Mock
    RequestRepository requestRepository;

    @Mock
    ProviderService providerService;

    @InjectMocks
    OfferService offerService;

    @Test
    public void testAcceptOffer() {
        String userId = "testUserId";

        String offerId = ("testOfferId");
        Integer addressId = (1);

        UserDetailDto userDetail = new UserDetailDto();
        userDetail.setUsername("testUsername");
        userDetail.setUserEmail("testEmail");
        UserPaymentMethodDto paymentMethodDto = new UserPaymentMethodDto();
        paymentMethodDto.setPayment_method("testPaymentMethod");

        List<UserPaymentMethodDto> paymentMethods = new ArrayList<>();
        paymentMethods.add(paymentMethodDto);

        userDetail.setUserPaymentMethod(paymentMethods);

//        userDetail.setUserPaymentMethod(List.of("testPaymentMethod"));
        UserAddressDto userAddressDto = new UserAddressDto();
        userAddressDto.setAddress("testAddress");
        List<UserAddressDto> userAddressDtos = new ArrayList<>();
        userAddressDtos.add(userAddressDto);
        userDetail.setUserAddress(userAddressDtos);

        Offer offer = new Offer();
        offer.setOfferId("testOfferId");
        offer.setPrice(100);
        offer.setProviderId("testProviderId");

        RequestModel request = new RequestModel();
        request.setItem("testItem");
        request.setQuantity("1");
        request.setPrimaryImage("testImage");

        ProviderDetailDto providerDetail = new ProviderDetailDto();
        providerDetail.setProviderName("testProviderName");
        providerDetail.setLocationName("testLocationName");

        when(userService.getUserDetailById(userId)).thenReturn(userDetail);
        when(offerRepository.findOfferByOfferId(offerId)).thenReturn(offer);
        when(requestRepository.findRequestModelByRequestId(offer.getRequestId())).thenReturn(request);
        when(providerService.getProviderDetail(offer.getProviderId())).thenReturn(providerDetail);

        PaymentDetailDto result = offerService.acceptOffer(userId, offerId, addressId);
        System.out.println(result);


        assertEquals("testOfferId", result.getOfferId());
        assertEquals("testUsername", result.getUserUsername());
        assertEquals("testEmail", result.getUserEmail());
        assertEquals("testAddress", result.getUserAddress().getAddress());
        assertEquals("testPaymentMethod", result.getUserPaymentMethod().getPayment_method());
        assertEquals("testProviderName", result.getProviderUsername());
        assertEquals("testLocationName", result.getLocation());
        assertEquals("testItem", result.getItem());
        assertEquals("1", result.getQuantity());
        assertEquals("testImage", result.getPrimary_image());
        assertEquals(100, result.getPrice());
    }
}