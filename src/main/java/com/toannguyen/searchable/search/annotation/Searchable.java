package com.toannguyen.searchable.search.annotation;

import com.toannguyen.searchable.search.SearchType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Searchable {
    SearchType value() default SearchType.JPA;
    Class<?> entity() default Void.class;
}
