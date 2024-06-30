package com.toannguyen.searchable.search;

import com.toannguyen.searchable.search.filter.Filter;
import com.toannguyen.searchable.search.filter.RangeFilter;
import com.toannguyen.searchable.search.filter.StringFilter;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class JooqQueryService {

    public <X, T extends Field<X>> Condition buildSpecification(final Filter<X> filter, final T field) {
        if (filter.getEquals() != null) {
            return field.equal(filter.getEquals());
        } else if (filter.getIn() != null) {
            return field.in(filter.getIn());
        }
        Condition condition = DSL.noCondition();
        if (filter.getNotEquals() != null) {
            condition = condition.and(field.notEqual(filter.getNotEquals()));
        }
        if (filter.getNotIn() != null) {
            condition = condition.and(field.notIn(filter.getNotIn()));
        }
        if (filter.getSpecified() != null) {
            condition = condition.and(filter.getSpecified() ? field.isNotNull() : field.isNull());
        }
        return condition;
    }

    public <X extends Comparable<? super X>, T extends Field<X>> Condition buildSpecification(final RangeFilter<X> filter, final T field) {
        if (filter.getEquals() != null) {
            return field.equal(filter.getEquals());
        } else if (filter.getIn() != null) {
            return field.in(filter.getIn());
        }

        Condition condition = DSL.noCondition();
        if (filter.getNotEquals() != null) {
            condition = condition.and(field.notEqual(filter.getNotEquals()));
        }
        if (filter.getNotIn() != null) {
            condition = condition.and(field.notIn(filter.getNotIn()));
        }
        if (filter.getSpecified() != null) {
            condition = condition.and(filter.getSpecified() ? field.isNotNull() : field.isNull());
        }
        if (filter.getGreaterThan() != null) {
            condition = condition.and(field.greaterThan(filter.getGreaterThan()));
        }
        if (filter.getGreaterThanOrEqual() != null) {
            condition = condition.and(field.greaterOrEqual(filter.getGreaterThanOrEqual()));
        }
        if (filter.getLessThan() != null) {
            condition = condition.and(field.lessThan(filter.getLessThan()));
        }
        if (filter.getLessThanOrEqual() != null) {
            condition = condition.and(field.lessOrEqual(filter.getLessThanOrEqual()));
        }
        return condition;
    }

    public <T extends Field<String>> Condition buildSpecification(final StringFilter filter, final T field) {
        if (filter.getEquals() != null) {
            return field.equalIgnoreCase(filter.getEquals());
        } else if (filter.getIn() != null) {
            return DSL.upper(field).in(filter.getIn().stream().map(String::toUpperCase).collect(Collectors.toSet()));
        }

        Condition condition = DSL.noCondition();
        if (filter.getNotEquals() != null) {
            condition = condition.and(field.notEqualIgnoreCase(filter.getNotEquals()));
        }
        if (filter.getNotIn() != null) {
            condition = condition.and(DSL.upper(field).notIn(filter.getNotIn().stream().map(String::toUpperCase).collect(Collectors.toSet())));
        }
        if (filter.getSpecified() != null) {
            condition = condition.and(filter.getSpecified() ? field.isNotNull() : field.isNull());
        }
        if (filter.getContains() != null) {
            condition = condition.and(field.containsIgnoreCase(filter.getContains()));
        }
        if (filter.getDoesNotContain() != null) {
            condition = condition.and(field.notContainsIgnoreCase(filter.getDoesNotContain()));
        }

        return condition;
    }
}