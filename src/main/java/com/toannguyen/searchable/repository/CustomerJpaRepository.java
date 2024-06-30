package com.toannguyen.searchable.repository;

import com.toannguyen.searchable.entity.CustomerJpa;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends CrudRepository<CustomerJpa, Long>, JpaSpecificationExecutor<CustomerJpa> {
}
