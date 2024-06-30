package com.toannguyen.searchable.search.filter;

/**
 * Filter class for {@link Long} type attributes.
 *
 * @see RangeFilter
 */
public class LongFilter extends RangeFilter<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for LongFilter.</p>
     */
    public LongFilter() {
    }

    /**
     * <p>Constructor for LongFilter.</p>
     *
     * @param filter a {@link LongFilter} object.
     */
    public LongFilter(final LongFilter filter) {
        super(filter);
    }

    /**
     * <p>copy.</p>
     *
     * @return a {@link LongFilter} object.
     */
    public LongFilter copy() {
        return new LongFilter(this);
    }

    public Class getOriginType() {
        return Long.class;
    }
}
