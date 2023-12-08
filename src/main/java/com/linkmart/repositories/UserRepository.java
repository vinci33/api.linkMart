package com.linkmart.repositories;

import com.linkmart.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,String> {
    @Query(value = """
           Select username from users where users.id = :created_by
            """, nativeQuery = true)
    String findByUserId(@Param("created_by") String created_by);
}