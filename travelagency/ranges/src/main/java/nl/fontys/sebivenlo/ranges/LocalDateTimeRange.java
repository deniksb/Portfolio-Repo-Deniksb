//Start Solution
package nl.fontys.sebivenlo.ranges;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * DateTime range (cf postgresql tsrange). This version only supports half open
 * ranges, i.e. '[)' meaning start is included, but end is not.
 *
 * Inspired by PostgreSQL TSRANGE.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class LocalDateTimeRange implements
        Range<LocalDateTimeRange, LocalDateTime, ChronoUnit> {

    public static final ChronoUnit DEFAULT_LENGTH_UNIT = ChronoUnit.MILLIS;

    /**
     * Helper constructor.
     *
     * @param start time
     * @param noLater end time
     * @return the time range
     */
    public static LocalDateTimeRange fromUntil( LocalDateTime start,
            LocalDateTime noLater ) {
        return new LocalDateTimeRange( start, noLater );
    }

    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Create a range with start and end. A negative duration will use start as
     * end and computes the start of this range by subtracting length from end.
     *
     * @param start of the range
     * @param length length of the range
     */
    public LocalDateTimeRange( LocalDateTime start, Duration length ) {
        this( Range.minmax( start, start.plus( length ) ) );
    }

    /**
     * Create a range with start and end. If end precedes start, an
     * IllegalArgument exception will be thrown
     *
     * @param start begin
     * @param end of range
     * @throws IllegalArgumentException when end precedes start.
     */
    public LocalDateTimeRange( LocalDateTime start, LocalDateTime end ) {
        this( Range.minmax( start, end ) );
    }

    /**
     * Helper to ensure natural boundary order.
     *
     * @param parts the endpoints as array size 2
     */
    private LocalDateTimeRange( LocalDateTime... parts ) {
        this.start = parts[ 0 ];
        this.end = parts[ 1 ];
    }

    public LocalDateTime getStart() {
        return start;
    }

    public Duration getLength() {
        Duration length = Duration.between( start, end );
        return length;
    }

    @Override
    public String toString() {
        return "[" + start.toString() + "," + end.toString() + ")";
    }

    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Check if a range is before a time. If the range is built of times A and
     * B, then the result is true for values &gt;= B;
     *
     * @param when the time
     * @return true when there is no overlap and when is after end of this
     * range.
     */
    public boolean isBefore( LocalDateTime when ) {
        return this.end.compareTo( when ) <= 0;
    }

    /**
     * Check if a range is after time. If the range is built of times A and B,
     * then the result is true for values &lt;= A;
     *
     * @param when the time
     * @return true when there is no overlap and when is before start of this
     * range.
     */
    public boolean isAfter( LocalDateTime when ) {
        return this.start.compareTo( when ) >= 0;
    }

    @Override
    public long getLength( ChronoUnit unit ) {
        return start.until( end, (ChronoUnit) unit );
    }

    /**
     * Return a function to measure using a.until(b, ChronoUnit). This method is
     * a higher order function: a function returning a function.
     *
     * @return function that computes the distance between a and b using a unit
     * u.
     */
    @Override
    public MeasureBetween<LocalDateTime, ChronoUnit> meter() {
        return ( a, b, u ) -> a.until( b, u );
    }

    static LocalDateTimeRange setFromString( String value ) {

        String[] part = value.replace( "\"", "" ).replace( "[", "" ).replace(
                ")", "" ).split( "," );
        var start = LocalDateTime.parse( part[ 0 ].replace( " ", "T" ) );
        var end = LocalDateTime.parse( part[ 1 ].replace( " ", "T" ) );
        return new LocalDateTimeRange( start, end );
    }

    @Override
    public ChronoUnit defaultUnit() {
        return ChronoUnit.SECONDS;
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
    public LocalDateTimeRange between( LocalDateTime start, LocalDateTime end ) {
        return new LocalDateTimeRange( start, end );
    }
}

//End Solution
