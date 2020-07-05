package nl.fontys.sebivenlo.ranges;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.stream.Stream;

/**
 * Range demarcated by two LocalDates, start inclusive, end exclusive.
 *
 * Inspired by PostgreSQL DateRange.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class LocalDateRange implements Range<LocalDateRange,LocalDate, ChronoUnit> {

    public static final ChronoUnit DEFAULT_LENGTH_UNIT = ChronoUnit.DAYS;

    /**
     * Helper constructor.
     *
     * @param start time inclusive
     * @param noLater end time exclusive
     * @return the time range
     */
    public static LocalDateRange fromUntil( LocalDate start, LocalDate noLater ) {
        return new LocalDateRange( start, noLater );
    }
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Create a range with start and end. A negative duration will use start as
     * end and computes the start of this range by subtracting length from end.
     *
     * @param start of the range
     * @param length length of the range
     */
    public LocalDateRange( LocalDate start, Period length ) {
        this( Range.minmax( start, start.plus( length ) ) );
    }

    /**
     * Create a range with start and end. If end precedes start, the parameters
     * are swapped.
     *
     * @param start begin
     * @param end of range
     * @throws IllegalArgumentException never.
     */
    public LocalDateRange( LocalDate start, LocalDate end ) {
        this( Range.minmax( start, end ) );
    }

    /**
     * Helper to ensure natural boundary order.
     *
     * @param dates the endpoints as array size 2
     *
     */
    private LocalDateRange( LocalDate... dates ) {
        this.start = dates[ 0 ];
        this.end = dates[ 1 ];
    }

    /**
     * The number of days in this range. For a normal year the value is 365
     * between this years Jan first and next year Jan first.
     *
     * @return the number of days.
     */
    public long getDays() {
        return getLength( DAYS );
    }

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public LocalDate getEnd() {
        return end;
    }

    /**
     * Check if a range is before a time. If the range is built of times A and
     * B, then the result is true for values of when &gt;= B;
     *
     * @param when the time
     * @return true when there is no overlap and 'when' is after end of this
     * range.
     */
    public boolean isBefore( LocalDate when ) {
        return this.end.compareTo( when ) <= 0;
    }

    /**
     * Check if a range is after time. If the range is built of times A and B,
     * then the result is true for values of when &lt;= B;
     *
     * @param when the time
     * @return true when there is no overlap and when is before start of this
     * range.
     */
    public boolean isAfter( LocalDate when ) {
        return this.start.compareTo( when ) >= 0;
    }

    /**
     * Measure using a.until(b, ChronoUnit)
     *
     * @return the distance between a and b
     */
    @Override
    public MeasureBetween<LocalDate, ChronoUnit> meter() {
        return ( a, b, u ) -> a.until( b, u );
    }

    @Override
    public ChronoUnit defaultUnit() {
        return DEFAULT_LENGTH_UNIT;
    }

    @Override
    public int hashCode() {
        return rangeHashCode();
    }

    @Override
    @SuppressWarnings( "EqualsWhichDoesntCheckParameterClass" )
    public boolean equals( Object obj ) {
        return rangeEquals( obj );
    }

    @Override
    public String toString() {
        return rangeToString();
    }

    @Override
    public LocalDateRange between( LocalDate start, LocalDate end ) {
        return new LocalDateRange( start, end );
    }
}
