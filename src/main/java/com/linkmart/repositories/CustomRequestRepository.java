package com.linkmart.repositories;

import com.linkmart.dtos.AnotherRequestDto;
import com.linkmart.dtos.RequestDto;
import com.linkmart.mappers.RequestMapper;
import com.linkmart.models.RequestModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomRequestRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public CustomRequestRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<AnotherRequestDto> searchRequest(List<String> categories, List<String> locations) {
        String query = """
                  SELECT
                      r.*
                FROM
                    request r
                JOIN
                    category c
                        ON r.category_id = c.id
                JOIN
                    location l
                        ON r.location_id = l.id
                WHERE c.category_name IN :categories and
                l.location_name IN :locations
                  """;
        var typedQuery =
                entityManager.createNativeQuery(query,
                RequestModel.class)
                .setParameter("categories",categories)
                .setParameter("locations",locations);
        var result = RequestMapper.INSTANCE.toAnotherRequestDto(typedQuery.getResultList());
        return result;
    }
}
