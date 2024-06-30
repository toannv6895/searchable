package com.toannguyen.searchable.mapper;

import com.toannguyen.searchable.dto.CustomerJpaDto;
import com.toannguyen.searchable.entity.CustomerJpa;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerJpaMapper {
    CustomerJpa toEntity(CustomerJpaDto dto);
    List<CustomerJpa> toEntity(List<CustomerJpaDto> dto);
    CustomerJpaDto toDto(CustomerJpa entity);
    List<CustomerJpaDto> toDto(List<CustomerJpa> entity);
}
