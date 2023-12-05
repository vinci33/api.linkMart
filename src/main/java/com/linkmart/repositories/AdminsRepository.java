package com.linkmart.repositories;

import com.linkmart.models.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminsRepository extends JpaRepository<Admins,String> {
}
