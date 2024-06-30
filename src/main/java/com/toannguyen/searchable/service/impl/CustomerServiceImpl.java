package com.toannguyen.searchable.service.impl;

import com.toannguyen.searchable.criteria.CustomerJpaCriteria;
import com.toannguyen.searchable.dto.CustomerJpaDto;
import com.toannguyen.searchable.entity.CustomerJpa;
import com.toannguyen.searchable.mapper.CustomerJpaMapper;
import com.toannguyen.searchable.repository.CustomerJpaRepository;
import com.toannguyen.searchable.search.SearchBuilder;
import com.toannguyen.searchable.service.CustomerJpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerJpaService {
    private final SearchBuilder searchBuilder;
    private final CustomerJpaRepository customerRepository;
    private final CustomerJpaMapper customerJpaMapper;
    @Override
    public void create(CustomerJpaDto customerJpaDto) {
        var customer = customerJpaMapper.toEntity(customerJpaDto);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerJpaDto> findAll(CustomerJpaCriteria customerJpaCriteria) {
        var condition = (Specification<CustomerJpa>) searchBuilder.createSearch(customerJpaCriteria);
        var customerList = customerRepository.findAll(condition);
        var customerListDto = customerJpaMapper.toDto(customerList);
        return customerListDto;
    }
}
