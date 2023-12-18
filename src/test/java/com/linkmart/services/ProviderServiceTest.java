package com.linkmart.services;

import com.linkmart.dtos.ProviderDetailDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProviderServiceTest {
    final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    ProviderService providerService;

//    @Test
//    public void testGetProviderDetail() {
//        String providerId = "01HHWT0SHQSC3JT138E5B5ANG0"; // replace with a valid provider ID
//        ProviderDetailDto providerDetailDto = providerService.getProviderDetail(providerId);
//        logger.info("ProviderDetailDto: " + providerDetailDto);
//        assertNotNull(providerDetailDto, "ProviderDetailDto should not be null");
//    }
}