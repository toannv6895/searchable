package com.toannguyen.searchable.mapper;

import com.toannguyen.searchable.dto.CustomerJooqDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import generated.model.tables.records.CustomerRecord;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerJooqMapper {
    CustomerRecord toEntity(CustomerJooqDto dto);
    List<CustomerRecord> toEntity(List<CustomerJooqDto> dto);
    CustomerJooqDto toDto(CustomerRecord entity);
    List<CustomerJooqDto> toDto(List<CustomerRecord> entity);
}
