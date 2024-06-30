package com.toannguyen.searchable.controller;

import com.toannguyen.searchable.criteria.CustomerJpaCriteria;
import com.toannguyen.searchable.dto.CustomerJpaDto;
import com.toannguyen.searchable.service.CustomerJpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/jpa/customers")
@RequiredArgsConstructor
public class CustomerJpaController {
    private final CustomerJpaService customerService;

    @PostMapping
    public ResponseEntity create(@RequestBody CustomerJpaDto customerJpaDto) {
        customerService.create(customerJpaDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerJpaDto>> getAll(CustomerJpaCriteria customerCriteria) {
        return ResponseEntity.ok(customerService.findAll(customerCriteria));
    }
}
