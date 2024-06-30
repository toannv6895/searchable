package com.toannguyen.searchable.search.filter;

/**
 * Class for filtering attributes with {@link Boolean} type. It can be added to a criteria class as a member, to support
 * the following query parameters:
 * <pre>
 *      fieldName.equals=true
 *      fieldName.notEquals=true
 *      fieldName.specified=true
 *      fieldName.specified=false
 *      fieldName.in=true,false
 *      fieldName.notIn=true,false
 * </pre>
 */
public class BooleanFilter extends Filter<Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for BooleanFilter.</p>
     */
    public BooleanFilter() {
    }

    /**
     * <p>Constructor for BooleanFilter.</p>
     *
     * @param filter a {@link BooleanFilter} object.
     */
    public BooleanFilter(final BooleanFilter filter) {
        super(filter);
    }

    /** {@inheritDoc} */
    @Override
    public BooleanFilter copy() {
        return new BooleanFilter(this);
    }

    public Class getOriginType() {
        return Boolean.class;
    }
}
