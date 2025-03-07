/*
 * This file is generated by jOOQ.
 */
package generated.model.tables.records;


import generated.model.tables.Customer;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CustomerRecord extends UpdatableRecordImpl<CustomerRecord> implements Record4<Long, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.customer.id</code>.
     */
    public CustomerRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.customer.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.customer.first_name</code>.
     */
    public CustomerRecord setFirstName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.customer.first_name</code>.
     */
    public String getFirstName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.customer.last_name</code>.
     */
    public CustomerRecord setLastName(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.customer.last_name</code>.
     */
    public String getLastName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.customer.email</code>.
     */
    public CustomerRecord setEmail(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.customer.email</code>.
     */
    public String getEmail() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Long, String, String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Customer.CUSTOMER.ID;
    }

    @Override
    public Field<String> field2() {
        return Customer.CUSTOMER.FIRST_NAME;
    }

    @Override
    public Field<String> field3() {
        return Customer.CUSTOMER.LAST_NAME;
    }

    @Override
    public Field<String> field4() {
        return Customer.CUSTOMER.EMAIL;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getFirstName();
    }

    @Override
    public String component3() {
        return getLastName();
    }

    @Override
    public String component4() {
        return getEmail();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getFirstName();
    }

    @Override
    public String value3() {
        return getLastName();
    }

    @Override
    public String value4() {
        return getEmail();
    }

    @Override
    public CustomerRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public CustomerRecord value2(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public CustomerRecord value3(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public CustomerRecord value4(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public CustomerRecord values(Long value1, String value2, String value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CustomerRecord
     */
    public CustomerRecord() {
        super(Customer.CUSTOMER);
    }

    /**
     * Create a detached, initialised CustomerRecord
     */
    public CustomerRecord(Long id, String firstName, String lastName, String email) {
        super(Customer.CUSTOMER);

        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }
}
