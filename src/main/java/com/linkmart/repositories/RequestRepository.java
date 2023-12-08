package com.linkmart.repositories;

import com.linkmart.models.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<RequestModel, Object> {
}
