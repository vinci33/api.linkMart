package com.linkmart.repositories;

import com.linkmart.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location,Integer> {
    @Query(value = """
            Select location_name from location where location.id = :location_id
             """, nativeQuery = true)
    String findByLocationId(Integer location_id);

    List<Location> findLocationById(Integer LocationId);
}
