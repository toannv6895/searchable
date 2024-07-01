package com.toannguyen.searchable.filter;

import com.toannguyen.searchable.criteria.CustomerJpaCriteria;
import com.toannguyen.searchable.entity.CustomerJpa;
import com.toannguyen.searchable.entity.CustomerJpa_;
import org.springframework.data.jpa.domain.Specification;

public class CustomerJpaFilter {
    public static Specification<CustomerJpa> toCondition(CustomerJpaCriteria customerCriteria) {
        Specification<CustomerJpa> spec = Specification.where(null);
        if (customerCriteria.getFirstName() != null) {
            String searchPattern = "%" + customerCriteria.getFirstName().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get(CustomerJpa_.firstName)), searchPattern));
        }
        if (customerCriteria.getLastName() != null) {
            String searchPattern = "%" + customerCriteria.getLastName().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get(CustomerJpa_.lastName)), searchPattern));
        }

        if (customerCriteria.getEmail() != null) {
            String searchPattern = "%" + customerCriteria.getEmail().toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get(CustomerJpa_.lastName)), searchPattern));
        }
        return spec;
    }
}

