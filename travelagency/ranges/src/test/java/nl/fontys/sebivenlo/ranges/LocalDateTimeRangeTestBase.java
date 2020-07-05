//Start Solution
package nl.fontys.sebivenlo.ranges;

import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class LocalDateTimeRangeTestBase extends RangeTestBase<LocalDateTime, ChronoUnit, LocalDateTimeRange> {

    static final LocalDateTime A = now( clock ).minus( 2, HOURS );
    static final LocalDateTime B = A.plus( 30, MINUTES );
    static final LocalDateTime C = B.plus( 10, MINUTES );
    static final LocalDateTime D = C.plus( 1, HOURS );
    static final LocalDateTime E = D.plus( 20, MINUTES );
    static final LocalDateTime F = E.plus( 3, HOURS );

    public LocalDateTimeRangeTestBase( ) {
        super( LocalDateTimeRange.class );
    }

    
    @Override
    LocalDateTime a() {
        return A;
    }

    @Override
    LocalDateTime b() {
        return B;
    }

    @Override
    LocalDateTime c() {
        return C;
    }

    @Override
    LocalDateTime d() {
        return D;
    }

    @Override
    LocalDateTime e() {
        return E;
    }
    @Override
    LocalDateTime f() {
        return F;
    }

    @Override
    LocalDateTimeRange createRange( LocalDateTime start, LocalDateTime end ) {
        return LocalDateTimeRange.fromUntil( start, end );
    }

    @Override
    ChronoUnit measurementUnit() {
        return LocalDateTimeRange.DEFAULT_LENGTH_UNIT;
    }
}
//End Solution
