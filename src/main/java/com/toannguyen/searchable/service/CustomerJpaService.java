package com.toannguyen.searchable.service;

import com.toannguyen.searchable.criteria.CustomerJpaCriteria;
import com.toannguyen.searchable.dto.CustomerJpaDto;

import java.util.List;


public interface CustomerJpaService {
    void create(CustomerJpaDto CustomerJpaDto);
    List<CustomerJpaDto> findAll(CustomerJpaCriteria customerJpaCriteria);
}
