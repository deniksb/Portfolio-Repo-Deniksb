package nl.fontys.sebivenlo.ranges;

import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.WEEKS;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class LocalDateRangeTestBase extends RangeTestBase<LocalDate, ChronoUnit, LocalDateRange> {

    static final LocalDate A = now( clock ).minus( 2, WEEKS );
    static final LocalDate B = A.plus( 3, DAYS );
    static final LocalDate C = B.plus( 10, DAYS );
    static final LocalDate D = C.plus( 1, WEEKS );
    static final LocalDate E = D.plus( 3, DAYS );
    static final LocalDate F = E.plus( 5, DAYS );

    public LocalDateRangeTestBase(
            ) {
        super( LocalDateRange.class );
    }

    
    @Override
    LocalDate a() {
        return A;
    }

    @Override
    LocalDate b() {
        return B;
    }

    @Override
    LocalDate c() {
        return C;
    }

    @Override
    LocalDate d() {
        return D;
    }

    @Override
    LocalDate e() {
        return E;
    }
 
    @Override
    LocalDate f() {
        return F;
    }

    @Override
    LocalDateRange createRange( LocalDate start, LocalDate end ) {
        return LocalDateRange.fromUntil( start, end );
    }

    @Override
    ChronoUnit measurementUnit() {
        return LocalDateRange.DEFAULT_LENGTH_UNIT;
    }
}
