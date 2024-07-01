package com.toannguyen.searchable.service.impl;

import com.toannguyen.searchable.criteria.CustomerJooqCriteria;
import com.toannguyen.searchable.dto.CustomerJooqDto;
import com.toannguyen.searchable.mapper.CustomerJooqMapper;
import com.toannguyen.searchable.repository.CustomerJooqRepository;
import com.toannguyen.searchable.search.SearchBuilder;
import com.toannguyen.searchable.service.CustomerJooqService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerJooqServiceImpl implements CustomerJooqService {
    private final SearchBuilder searchBuilder;
    private final CustomerJooqRepository customerRepository;
    private final CustomerJooqMapper customerJooqMapper;
    private final CustomerJooqRepository customerJooqRepository;

    @Override
    @Transactional
    public void create(CustomerJooqDto customerJooqDto) {
        var customer = customerJooqMapper.toEntity(customerJooqDto);

        customerRepository.save(customer);
    }

    @Override
    public List<CustomerJooqDto> findAll(CustomerJooqCriteria customerJooqCriteria) {
        var condition = (Condition) searchBuilder.createSearch(customerJooqCriteria);
        var customerList = customerJooqRepository.findAll(condition);
        var customerListDto = customerJooqMapper.toDto(customerList);
        return customerListDto;
    }
}
