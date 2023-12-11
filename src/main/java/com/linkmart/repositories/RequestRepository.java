package com.linkmart.repositories;

import com.linkmart.models.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequestRepository extends JpaRepository<RequestModel, Integer> {
    @Query(value = """
           Select * from request where created_by = :userId
            """, nativeQuery = true)
    RequestModel findRequestByUserId(@Param("userId") String userId);
}
