package com.linkmart.repositories;

import com.linkmart.models.Offer;
import com.linkmart.models.Provider;
import com.linkmart.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProviderRepository extends JpaRepository<Provider,String> {
    @Query(value = """
                    SELECT
                        provider.id
                        FROM provider
                        WHERE provider.user_id = :userId
            """, nativeQuery = true)
    String getIdByUserId(@Param("userId")String userId);

    Provider findProviderByUserId(String userId);
    Provider findProviderById(String id);
}
