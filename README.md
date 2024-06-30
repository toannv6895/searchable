# searchable
Example Implementation of Search with JPA and jOOQ.
Inspired by JHipster BOM https://github.com/jhipster/jhipster-bom

# How to use searchable?

## Class-Level Annotations
The @Searchable annotation is applied at the class level and includes the following attributes:

**value**: Defines the type of search to be used, either SearchType.JPA or SearchType.JOOQ.
**entity**: Specifies the entity meta-class associated with this criteria class.
This is only applicable for the JPA search type.

### Example:
`@Searchable(value = SearchType.JPA, entity = Customer_.class)`

`@Searchable(value = SearchType.JOOQ)`

## Field-Level Annotations
@IgnoreSearch: Fields marked with this annotation will be excluded from the search criteria and will not be considered during search operations.

@SearchOption: This annotation is used to map a field in the criteria class to a specific attribute in the target entity.

### Example:

`@SearchOption(alias = Customer_.NAME)`
In this example, the customerName field is mapped to the NAME attribute in the Customer_ entity.
If no alias is provided, the field name will be automatically converted from camelCase to snake_case for mapping.

@CustomSearch: Indicates that a custom search logic method should be used for this field.
The value specifies the name of the method that contains the custom search logic.

### Example:

`@CustomSearch(value = "getIncludeDeleteCondition")`
The custom search logic is implemented in the getIncludeDeleteCondition method.
This method adds additional conditions to the search criteria based on the value of the includeDelete field.

## Step-by-Step Usage
Step 1: Create a criteria class
### Example:
```@Getter
@Setter
@Searchable(value = SearchType.JPA, entity = Customer_.class)
public class CustomerCriteria {
@IgnoreSearch
private IntegerFilter customerUniqueID;
private StringFilter name;
@IgnoreSearch
private StringFilter address;
private BooleanFilter deleted;
@IgnoreSearch
@CustomSearch(value = "getIncludeDeleteCondition")
private BooleanFilter includeDelete;

protected Specification<Customer> getIncludeDeleteCondition(Specification specification, JpaQueryService jpaQueryService) {
    if (includeDelete != null && !includeDelete.getEquals()) {
        var deleteFilter = new BooleanFilter();
        deleteFilter.setEquals(Boolean.FALSE);
        specification = specification.and(jpaQueryService.buildSpecification(deleteFilter, Customer_.deleted));
    }

    return specification;
}
}
```


Step2: User search builder and criteria class to create search and get specification (JPA) or condition (JOOQ)
### Example:
`var condition = (Specification<Customer>) searchBuilder.createSearch(customerCriteria);`

APIs:

