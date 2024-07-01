package com.toannguyen.searchable.search;

import com.toannguyen.searchable.exception.BadRequestException;
import com.toannguyen.searchable.search.annotation.CustomSearch;
import com.toannguyen.searchable.search.annotation.IgnoreSearch;
import com.toannguyen.searchable.search.annotation.SearchOption;
import com.toannguyen.searchable.search.annotation.Searchable;
import com.toannguyen.searchable.search.filter.Filter;
import com.toannguyen.searchable.search.filter.RangeFilter;
import com.toannguyen.searchable.search.filter.StringFilter;
import jakarta.persistence.metamodel.SingularAttribute;
import org.jooq.Condition;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class SearchBuilder<C, R> {

    @Autowired
    private JpaQueryService jpaQueryService;

    @Autowired
    private JooqQueryService jooqQueryService;

    public R createSearch(C criteria) {
        if (criteria == null) {
            return null;
        }

        Class<?> criteriaClass = criteria.getClass();
        if (!criteriaClass.isAnnotationPresent(Searchable.class)) {
            return null;
        }

        Searchable searchableAnnotation = criteriaClass.getAnnotation(Searchable.class);
        SearchType searchType = searchableAnnotation.value();

        if (searchType == SearchType.JPA) {
            return buildJpaSpecification(criteria, searchableAnnotation);
        } else {
            return buildJooqCondition(criteria);
        }
    }

    private R buildJpaSpecification(C criteria, Searchable searchableAnnotation) {
        Specification<?> specification = Specification.where(null);

        for (Field field : criteria.getClass().getDeclaredFields()) {
            String fieldName = field.getName();
            try {
                field.setAccessible(true);

                if (field.isAnnotationPresent(IgnoreSearch.class)) {
                    continue;
                }

                if (field.isAnnotationPresent(CustomSearch.class)) {
                    CustomSearch customSearch = field.getAnnotation(CustomSearch.class);
                    var methodName = customSearch.value();
                    Class<?>[] parameterType = {Specification.class, JpaQueryService.class};
                    var customMethod = criteria.getClass().getDeclaredMethod(methodName, parameterType);
                    customMethod.setAccessible(true);
                    specification = (Specification<?>) customMethod.invoke(criteria, specification, jpaQueryService);
                }
                else {
                    Object value = field.get(criteria);
                    if (value != null) {
                        Class<?> entity = searchableAnnotation.entity();
                        if (field.isAnnotationPresent(SearchOption.class)) {
                            SearchOption searchOption = field.getAnnotation(SearchOption.class);
                            fieldName = searchOption.alias();
                        }
                        Field entityField = entity.getDeclaredField(fieldName);
                        entityField.setAccessible(true);
                        Object singularAttribute = entityField.get(null);
                        if (!(singularAttribute instanceof SingularAttribute)) {
                            continue;
                        }

                        specification = addJpaFilterToSpecification(specification, value, (SingularAttribute<?, ?>) singularAttribute);
                    }
                }
            }
            catch (Exception e) {
                throw new BadRequestException(String.format("Something wrong with parameter: %s", fieldName));
            }

        }
        return (R) specification;
    }

    private Specification<?> addJpaFilterToSpecification(Specification<?> specification, Object value, SingularAttribute<?, ?> attribute) {
        if (value instanceof StringFilter) {
            return specification.and(jpaQueryService.buildStringSpecification((StringFilter) value, attribute));
        } else if (value instanceof RangeFilter) {
            return specification.and(jpaQueryService.buildRangeSpecification((RangeFilter) value, attribute));
        }
        else if (value instanceof Filter) {
            return specification.and(jpaQueryService.buildSpecification((Filter) value, attribute));
        }

        return specification;
    }

    private R buildJooqCondition(C criteria) {
        Condition condition = DSL.noCondition();
        for (Field field : criteria.getClass().getDeclaredFields()) {
            String fieldName = field.getName();
            try {
                field.setAccessible(true);
                if (field.isAnnotationPresent(IgnoreSearch.class)) {
                    continue;
                }

                if (field.isAnnotationPresent(CustomSearch.class)) {
                    CustomSearch customSearch = field.getAnnotation(CustomSearch.class);
                    var methodName = customSearch.value();
                    Class<?>[] parameterTypes = {Condition.class, JooqQueryService.class};
                    var customMethod = criteria.getClass().getDeclaredMethod(methodName, parameterTypes);
                    customMethod.setAccessible(true);
                    condition = (Condition) customMethod.invoke(criteria, condition, jooqQueryService);
                }
                else {
                    Object value = field.get(criteria);
                    if (value != null) {
                        condition = addJooqFilterToCondition(condition, field, (Filter) value);
                    }
                }
            }
            catch (Exception e) {
                throw new BadRequestException(String.format("Something wrong with parameter: %s", fieldName));
            }
        }
        return (R) condition;
    }

    private Condition addJooqFilterToCondition(Condition condition, Field field, Filter filter) {
        SearchOption jooqFieldAnnotation = field.getAnnotation(SearchOption.class);
        org.jooq.Field jooqField;
        if (jooqFieldAnnotation != null) {
            String valueAnnotation = jooqFieldAnnotation.alias();
            var type = filter.getOriginType();
            jooqField = DSL.field(valueAnnotation, type);
        }
        else {
            String fieldName = convertCamelCaseToSnakeCase(field.getName());
            var type = filter.getOriginType();
            jooqField = DSL.field(fieldName, type);
        }

        if (filter instanceof StringFilter stringFilter) {
            condition = condition.and(jooqQueryService.buildSpecification(stringFilter, jooqField));
        } else if (filter instanceof RangeFilter rangeFilter) {
            condition = condition.and(jooqQueryService.buildSpecification(rangeFilter, jooqField));
        }
        else {
            condition = condition.and(jooqQueryService.buildSpecification(filter, jooqField));
        }

        return condition;
    }

    private String convertCamelCaseToSnakeCase(String camelCase) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return camelCase.replaceAll(regex, replacement).toLowerCase();
    }
}
