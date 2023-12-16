package com.linkmart.repositories;

import com.linkmart.models.ImageModel;
import com.linkmart.models.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ImageRepository extends JpaRepository<ImageModel, Integer> {
    @Modifying
    @Query(value = """
           UPDATE image SET is_active = false
           WHERE image.id = :imageId
            """, nativeQuery = true)
    void updateImageIsActiveByImageId(@Param("imageId") Integer imageId);
}
