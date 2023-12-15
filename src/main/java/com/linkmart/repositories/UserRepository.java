package com.linkmart.repositories;

import com.linkmart.dtos.UserWithProviderIdDto;
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

    @Query(value = """
            SELECT users.username, provider.id AS provider_id
            FROM users
            LEFT JOIN provider ON users.id = provider.user_id
            GROUP BY users.id, provider.id
            """, nativeQuery = true)
    List<UserWithProviderIdDto> getAllUserWithProviderId();
}