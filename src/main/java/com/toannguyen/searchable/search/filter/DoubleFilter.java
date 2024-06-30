package com.toannguyen.searchable.search.filter;

/**
 * Filter class for {@link Double} type attributes.
 *
 * @see RangeFilter
 */
public class DoubleFilter extends RangeFilter<Double> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for DoubleFilter.</p>
     */
    public DoubleFilter() {
    }

    /**
     * <p>Constructor for DoubleFilter.</p>
     *
     * @param filter a {@link DoubleFilter} object.
     */
    public DoubleFilter(final DoubleFilter filter) {
        super(filter);
    }

    /**
     * <p>copy.</p>
     *
     * @return a {@link DoubleFilter} object.
     */
    public DoubleFilter copy() {
        return new DoubleFilter(this);
    }

    public Class getOriginType() {
        return Double.class;
    }

}
