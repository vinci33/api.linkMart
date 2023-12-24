package com.linkmart.dtos;

import java.util.List;

public record RequestFilterDto (Integer totalRecords, Integer totalPages, List<AnotherRequestDto> requests) {
}
