package com.linkmart.repositories;

import com.linkmart.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, String> {
    Offer findByRequestId(String requestId);
}
