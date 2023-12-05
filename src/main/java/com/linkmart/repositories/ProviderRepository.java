package com.linkmart.repositories;

import com.linkmart.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider,String> {
}
