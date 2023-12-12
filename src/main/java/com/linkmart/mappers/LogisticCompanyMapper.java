package com.linkmart.mappers;

import com.linkmart.dtos.LocationDto;
import com.linkmart.dtos.LogisticCompanyDto;
import com.linkmart.models.Location;
import com.linkmart.models.LogisticCompany;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LogisticCompanyMapper {
    LogisticCompanyMapper INSTANCE = Mappers.getMapper(LogisticCompanyMapper.class);

    List<LogisticCompanyDto> getAllLogisticCompany (List<LogisticCompany> logisticCompanies);
}
