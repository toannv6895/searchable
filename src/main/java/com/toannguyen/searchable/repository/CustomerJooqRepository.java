package com.toannguyen.searchable.repository;

import generated.model.tables.records.CustomerRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static generated.model.Tables.CUSTOMER;

@Repository
@RequiredArgsConstructor
public class CustomerJooqRepository {
    private final DSLContext dslContext;

    public void save(CustomerRecord record) {
        dslContext
                .insertInto(CUSTOMER)
                .values(record)
                .execute();
    }

    public List<CustomerRecord> findAll(Condition condition) {
        List<CustomerRecord> records = dslContext
                .selectFrom(CUSTOMER)
                .where(condition)
                .fetch();

        return records;
    }
}
