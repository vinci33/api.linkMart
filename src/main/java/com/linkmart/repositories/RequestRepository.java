package com.linkmart.repositories;

import com.linkmart.dtos.RequestDto;
import com.linkmart.models.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<RequestModel, Integer> {
    @Query(value = """
           Select * from request where created_by = :userId
            """, nativeQuery = true)
    RequestModel findRequestByUserId(@Param("userId") String userId);

    @Query(value = """
           Select request.*, location_name 
           from request JOIN location 
           ON location_id = location.id limit 30
            """, nativeQuery = true)
    List<RequestModel> getAllRequest();
}
