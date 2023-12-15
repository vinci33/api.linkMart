package com.linkmart.repositories;

import com.linkmart.models.LogisticCompany;
import com.linkmart.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface offerRepository extends JpaRepository<Offer, Integer> {
}
