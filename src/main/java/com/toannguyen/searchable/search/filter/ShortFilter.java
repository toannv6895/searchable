package com.toannguyen.searchable.search.filter;

/**
 * Filter class for {@link Short} type attributes.
 *
 * @see RangeFilter
 */
public class ShortFilter extends RangeFilter<Short> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for ShortFilter.</p>
     */
    public ShortFilter() {
    }

    /**
     * <p>Constructor for ShortFilter.</p>
     *
     * @param filter a {@link ShortFilter} object.
     */
    public ShortFilter(final ShortFilter filter) {
        super(filter);
    }

    /**
     * <p>copy.</p>
     *
     * @return a {@link ShortFilter} object.
     */
    public ShortFilter copy() {
        return new ShortFilter(this);
    }

    public Class getOriginType() {
        return Short.class;
    }

}
