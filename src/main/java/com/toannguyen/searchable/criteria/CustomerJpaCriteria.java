package com.toannguyen.searchable.criteria;

import com.toannguyen.searchable.entity.CustomerJpa_;
import com.toannguyen.searchable.search.JpaQueryService;
import com.toannguyen.searchable.search.SearchType;
import com.toannguyen.searchable.search.annotation.CustomSearch;
import com.toannguyen.searchable.search.annotation.Searchable;
import com.toannguyen.searchable.search.filter.StringFilter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
@Searchable(value = SearchType.JPA, entity = CustomerJpa_.class)
public class CustomerJpaCriteria {
    private StringFilter firstName;
    private StringFilter lastName;
    private StringFilter email;
    @CustomSearch(value = "getFirstNameOrLastNameCondition")
    private StringFilter firstNameOrLastName;

    protected Specification getFirstNameOrLastNameCondition(Specification specification, JpaQueryService jpaQueryService) {
        if (firstNameOrLastName != null) {
            specification = specification
                    .and(jpaQueryService.buildStringSpecification(firstNameOrLastName, CustomerJpa_.firstName)
                        .or(jpaQueryService.buildStringSpecification(firstNameOrLastName, CustomerJpa_.lastName)));
        }

        return specification;
    }
}
