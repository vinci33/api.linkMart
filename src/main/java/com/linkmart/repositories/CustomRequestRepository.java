package com.linkmart.repositories;


import com.linkmart.dtos.AnotherRequestDto;
import com.linkmart.mappers.RequestMapper;
import com.linkmart.models.RequestModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomRequestRepository {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public List<AnotherRequestDto> searchRequest(List<String> categories, List<String> locations, Integer limit, Integer offset) {

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT r.* ");
        queryBuilder.append("FROM request r ");
        queryBuilder.append("JOIN category c ON r.category_id = c.id ");
        queryBuilder.append("JOIN location l ON r.location_id = l.id ");
        queryBuilder.append("WHERE is_active = true ");

        if (categories != null && !categories.isEmpty()) {
            String categoriesClause = categories.stream()
                    .map(category -> "c.category_name = '" + category + "'")
                    .collect(Collectors.joining(" OR "));
            queryBuilder.append("AND (").append(categoriesClause).append(") ");
        }
        if (locations != null && !locations.isEmpty()) {
            String locationsClause = locations.stream()
                    .map(location -> "l.location_name = '" + location + "'")
                    .collect(Collectors.joining(" OR "));
            queryBuilder.append("AND (").append(locationsClause).append(") ");
        }
        queryBuilder.append("ORDER BY r.updated_at DESC");
        if (limit != null) {
            queryBuilder.append(" LIMIT ").append(limit);
        }
        if (offset != null) {
            queryBuilder.append(" OFFSET ").append(offset);
        }

        String query = queryBuilder.toString();
        var typedQuery =
                entityManager.createNativeQuery(query,
                                RequestModel.class);

        var result = RequestMapper.INSTANCE.toAnotherRequestDto(typedQuery.getResultList());
        return result;
    }
}




