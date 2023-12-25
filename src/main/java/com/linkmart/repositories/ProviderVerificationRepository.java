package com.linkmart.repositories;

import com.linkmart.dtos.VerificationDto;
import com.linkmart.models.ProviderVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProviderVerificationRepository extends JpaRepository<ProviderVerification, String> {

    @Transactional
    @Query(value = """
            SELECT
                pv.id as verificationId,
                s.status_name as statusName,
                pv.id_document as idDocument,
                pv.address_document as addressDocument,
                pv.bank_document as bankDocument
            FROM provider_verification pv
            LEFT JOIN Status s ON pv.status_id = s.id
            WHERE pv.user_id = :UserId
            """, nativeQuery = true)
    VerificationDto findProviderVerificationByUserId(String UserId);
}
