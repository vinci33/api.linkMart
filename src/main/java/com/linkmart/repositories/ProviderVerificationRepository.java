package com.linkmart.repositories;

import com.linkmart.dtos.VerificationDto;
import com.linkmart.models.ProviderVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProviderVerificationRepository extends JpaRepository<ProviderVerification, String> {

    @Query(value = """
            SELECT
                pv.id as verificationId,
                s.name as statusName,
                pv.id_document as idDocument,
                pv.address_document as addressDocument,
                pv.bank_document as bankDocument
            FROM ProviderVerification pv
            JOIN Status s ON pv.status_id = s.id
            WHERE pv.userId = :UserId
            """, nativeQuery = true)
    VerificationDto findProviderVerificationByUserId(String UserId);
}
