package nl.fontys.sebivenlo.ranges;

import java.time.LocalDate;
import static java.time.LocalDate.of;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.ChronoUnit.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class LocalDateRangeTest extends LocalDateRangeTestBase {


    // 1999-1-1:11:25:13;
    static long ORIGIN = of( 1999, 1, 1 ).toEpochDay() * 24 * 3600 + 11 * 60 * 60 + 25 * 60 + 13;

//    @Ignore( "Think TDD" )
    @Test
    public void testHashCode() {
        int h0 = LocalDateRange.fromUntil( A, B ).hashCode();
        int h1 = LocalDateRange.fromUntil( A, B ).hashCode();
        
        assertThat( h1 ).isEqualTo( h0 );
//        Assert.fail( "method testHashCode reached end. You know what to do." );
    }

//    @Ignore( "Think TDD" )
    @Test
    public void testUsingDuration() {
        Period day5 = Period.ofDays( 5 );
        LocalDateRange ts = new LocalDateRange( A, day5 );
        assertThat( ts.getLength( DAYS ) ).isEqualTo( 5 );
//        Assert.fail( "method testUsingDuration reached end. You know what to do." );
    }

//    @Ignore( "Think TDD" )
    public void rangeIsNormalised() {
        assertThat( new LocalDateRange( A, B ) ).isEqualTo( new LocalDateRange( B, A ) );
//        Assert.fail( "method rangeIsNormalised reached end. You know what to do." );
    }

//    @Ignore( "Think TDD" )
    @Test
    public void negativeDurationStartEarlier() {
        LocalDateRange ts0 = new LocalDateRange( B, Period.ofDays( -3 ) );
        
        assertThat( ts0.getStart() ).isEqualTo( A );
//        Assert.fail( "method negativeDurationStartEarlier reached end. You know what to do." );
    }

//    @Ignore( "Think TDD" )
    @Test
    public void testBefore() {
        LocalDateRange ts0 = new LocalDateRange( B, C );
        System.out.println( "ts0 = " + ts0 );
        assertThat( ts0.isBefore( D ) ).isTrue();
        assertThat( ts0.isBefore( A ) ).isFalse();
//        Assert.fail( "method testIsAfter reached end. You know what to do." );
    }

//    @Ignore( "Think TDD" )
    @Test
    public void testAfter() {
        LocalDateRange ts0 = new LocalDateRange( A, B );
        System.out.println( "ts0 = " + ts0 );
        assertThat( ts0.isAfter( A ) ).isTrue();
        assertThat( ts0.isAfter( B ) ).isFalse();
//        Assert.fail( "method testIsAfter reached end. You know what to do." );
    }

//    @Ignore( "Think TDD" )
    @Test
    public void testPointInRange() {
        LocalDateRange range = new LocalDateRange( A, B );
        LocalDate A1 = A.plusDays( A.until( B, DAYS ) / 2 );
        assertThat( range.contains( A1 ) ).isTrue();

//        Assert.fail( "method testPointInRange reached end. You know what to do." );
    }
//    @Ignore( "Think TDD" )

    @Test
    public void startPointInRange() {
        LocalDateRange range = new LocalDateRange( A, B );
        
        assertThat( range.contains( A ) ).isTrue();
//        Assert.fail( "method startPointInRange reached end. You know what to do." );
    }

//    @Ignore( "Think TDD" )
    @Test
    public void getPeriod() {
        LocalDateRange range = new LocalDateRange( B, A ); // flipped to check normalize
        System.out.println( "A = " + A );
        
        System.out.println( "B = " + B );
        long p = range.getLength( ChronoUnit.DAYS );
        System.out.println( "p = " + p );
        assertThat( p ).isEqualTo( 3 );
//        Assert.fail( "method getPeriod reached end. You know what to do." );
    }
//    @Ignore( "Think TDD" )

    @Test
    public void getLength() {
        LocalDateRange range = new LocalDateRange( B, C );
        
        assertThat( range.getDays() ).isEqualTo( 10 );
        
        LocalDate d1 = of( 2019, 4, 12 );
        LocalDate d2 = of( 2019, 5, 28 );
        range = new LocalDateRange( d1, d2 );
        assertThat( range.getDays() ).isEqualTo( 16 + 30 );
//        Assert.fail( "method getLength reached end. You know what to do." );
    }

//    @Ignore( "Think TDD" )
    @Test
    public void intersections() throws Exception {
        LocalDateRange range1 = new LocalDateRange( A, B );
        LocalDateRange range2 = new LocalDateRange( C, D );
//        assertThat( range1.overlap( range2, DAYS ) ).isEqualTo( 0 );
        
        LocalDateRange range3 = new LocalDateRange( A, D );
        
//        assertThat( range3.overlap( range1, DAYS ) )
//                .isEqualTo( range1.getLength( DAYS ) );
//        LocalDateRange range4 = new LocalDateRange( B, C );
//        assertThat( range3.overlap( range4, DAYS ) )
//                .isEqualTo( range4.getLength( DAYS ) );
//        assertThat( range4.overlap( range3, DAYS ) )
//                .isEqualTo( range4.getLength( DAYS ) );
//        Assert.fail( "method intersections reached end. You know what to do." );
    }
    
    @ParameterizedTest
    @CsvSource( {
        "abc,true, ab is before c",
        "acb,false, b is between ac",
        "bca,false, bc is not before a", 
        "abb,true, ab is before b", 
    } )
    public void isBefore( String label, boolean expected, String msg ) {
        
        List<LocalDate> pt2 = pointList( label );
        LocalDate a = pt2.get( 0 ), b = pt2.get( 1 ), c = pt2.get( 2 );
        LocalDateRange r = createRange( a, b );
        
        assertThat( r.isBefore( c ) ).as( msg ).isEqualTo( expected );

//        fail( "method isBefore reached end. You know what to do." );
    }
    
}
