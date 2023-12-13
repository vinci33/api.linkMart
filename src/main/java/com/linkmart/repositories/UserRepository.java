package com.linkmart.repositories;

import com.linkmart.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {


    @Query(value = """
           Select username from users where users.id = :created_by
            """, nativeQuery = true)
    String findByUserId(@Param("created_by") String created_by);

    List<User> findUserByUsername(String username);

    List<User>findUserByUserEmail(String userEmail);

    void deleteByUserEmail(String userEmail);

    User findUserById(String Id);
}