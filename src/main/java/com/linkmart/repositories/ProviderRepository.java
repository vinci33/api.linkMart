package com.linkmart.repositories;

import com.linkmart.dtos.ProviderDashboardDto;
import com.linkmart.models.Offer;
import com.linkmart.models.Provider;
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

    @Query(value = """
                    SELECT
                        provider.star_of_efficiency AS AverageEfficiency,
                        provider.star_of_attitude AS AverageAttitude,
                        provider.number_of_reviews AS ReviewCount
                    FROM provider
                    WHERE provider.id = :providerId
            """, nativeQuery = true)
    ProviderDashboardDto findDashBoardByProviderId(@Param("providerId")String providerId);

    @Modifying
    @Query(value = """
                    UPDATE provider
                    SET star_of_efficiency = :starOfEfficiency,
                        star_of_attitude = :starOfAttitude
                    WHERE provider.id = :providerId
            """, nativeQuery = true)
    void updateProviderRating(@Param("providerId")String providerId,
                                     @Param("starOfEfficiency")Float averageEfficiency,
                                     @Param("starOfAttitude")Float averageAttitude);
}
