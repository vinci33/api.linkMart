package com.linkmart.repositories;

import com.linkmart.models.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageModel, Integer> {
}
