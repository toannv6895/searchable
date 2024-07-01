package com.toannguyen.searchable.criteria;

import com.toannguyen.searchable.search.JooqQueryService;
import com.toannguyen.searchable.search.SearchType;
import com.toannguyen.searchable.search.annotation.CustomSearch;
import com.toannguyen.searchable.search.annotation.Searchable;
import com.toannguyen.searchable.search.filter.StringFilter;
import lombok.Getter;
import lombok.Setter;
import org.jooq.Condition;

import static generated.model.Tables.CUSTOMER;

@Getter
@Setter
@Searchable(value = SearchType.JOOQ)
public class CustomerJooqCriteria {
    private StringFilter firstName;
    private StringFilter lastName;
    private StringFilter email;
    @CustomSearch(value = "getFirstNameOrLastNameCondition")
    private StringFilter firstNameOrLastName;

    protected Condition getFirstNameOrLastNameCondition(Condition condition, JooqQueryService jooqQueryService) {
        if (firstNameOrLastName != null) {
            condition = condition
                    .and(jooqQueryService.buildSpecification(firstNameOrLastName, CUSTOMER.FIRST_NAME)
                            .or(jooqQueryService.buildSpecification(firstNameOrLastName, CUSTOMER.LAST_NAME)));
        }

        return condition;
    }
}
