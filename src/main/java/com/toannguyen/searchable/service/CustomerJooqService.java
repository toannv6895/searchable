package com.toannguyen.searchable.service;

import com.toannguyen.searchable.criteria.CustomerJooqCriteria;
import com.toannguyen.searchable.dto.CustomerJooqDto;

import java.util.List;


public interface CustomerJooqService {
    void create(CustomerJooqDto CustomerJooqDto);
    List<CustomerJooqDto> findAll(CustomerJooqCriteria customerJooqCriteria);
}
