package com.linkmart.repositories;

import com.linkmart.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StatusRepository extends JpaRepository<Status, Integer> {
    @Query(value = """
            SELECT status_name
            FROM status
            WHERE status.id = :id""", nativeQuery = true)
    String findStatusName (@Param("id") Integer id);
}
