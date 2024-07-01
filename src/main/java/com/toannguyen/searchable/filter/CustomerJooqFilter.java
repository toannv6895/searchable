package com.toannguyen.searchable.filter;

import com.toannguyen.searchable.criteria.CustomerJooqCriteria;
import org.jooq.Condition;

import static generated.model.Tables.CUSTOMER;
import static org.jooq.impl.DSL.noCondition;

public class CustomerJooqFilter {
    public static Condition toCondition(CustomerJooqCriteria customerCriteria) {
        var condition = noCondition();
        if (customerCriteria.getFirstName() != null) {
            condition = condition.and(CUSTOMER.FIRST_NAME.containsIgnoreCase(customerCriteria.getFirstName()));
        }

        if (customerCriteria.getLastName() != null) {
            condition = condition.and(CUSTOMER.FIRST_NAME.containsIgnoreCase(customerCriteria.getLastName()));
        }

        if (customerCriteria.getEmail() != null) {
            condition = condition.and(CUSTOMER.FIRST_NAME.containsIgnoreCase(customerCriteria.getEmail()));
        }

        return condition;
    }
}
