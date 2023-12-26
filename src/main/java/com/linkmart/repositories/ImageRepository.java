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

    @Query(value = """
           SELECT image.request_id FROM image
           WHERE image.id = :imageId
           """, nativeQuery = true)
    String findRequestIdByImageId(@Param("imageId") Integer imageId);

    @Query(value = """
           SELECT
           id
           FROM image
           WHERE image.request_id = :requestId
           """, nativeQuery = true)
    List<Integer> findImageByRequestId(@Param("requestId") String requestId);

    @Query(value = """
           SELECT
           image.image_path
           FROM image
           WHERE image.id = :imageId
           """, nativeQuery = true)
    String findImagePathByImageId(@Param("imageId") Integer imageId);

    @Modifying
    @Query(value = """
           UPDATE image SET is_primary = false
           WHERE image.id = :imageId
            """, nativeQuery = true)
    void updateImageIsNotPrimaryByImageId(@Param("imageId") Integer imageId);

    @Modifying
    @Query(value = """
           UPDATE image
           SET is_primary = true
           WHERE image.id = :imageId
            """, nativeQuery = true)
    void updateImageIsPrimaryByImageId(@Param("imageId") Integer imageId);
}
