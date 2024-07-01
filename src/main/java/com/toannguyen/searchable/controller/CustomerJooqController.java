package com.toannguyen.searchable.controller;

import com.toannguyen.searchable.criteria.CustomerJooqCriteria;
import com.toannguyen.searchable.criteria.CustomerJpaCriteria;
import com.toannguyen.searchable.dto.CustomerJooqDto;
import com.toannguyen.searchable.dto.CustomerJpaDto;
import com.toannguyen.searchable.service.CustomerJooqService;
import com.toannguyen.searchable.service.CustomerJpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/jooq/customers")
@RequiredArgsConstructor
public class CustomerJooqController {
    private final CustomerJooqService customerService;

//    @PostMapping
//    public ResponseEntity create(@RequestBody CustomerJooqDto customerJpaDto) {
//        customerService.create(customerJpaDto);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping
    public ResponseEntity<List<CustomerJooqDto>> getAll(CustomerJooqCriteria customerCriteria) {
        return ResponseEntity.ok(customerService.findAll(customerCriteria));
    }
}
