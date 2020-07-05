//Start Solution
package nl.fontys.sebivenlo.ranges;

import java.time.Duration;
import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class LocalDateTimeRangeTest extends LocalDateTimeRangeTestBase {

//    @Ignore( "Think TDD" )
    @Test
    public void testUsingDuration() {
        Duration min5 = Duration.of( 5, MINUTES );
        LocalDateTimeRange ts = new LocalDateTimeRange( A, Duration
                .of( 5, MINUTES ) );
        assertThat( ts.getLength() ).isEqualTo( min5 );
//        Assert.fail( "method testUsingDuration reached end. You know what to do." );
    }

//    @Ignore( "Think TDD" )
    @Test
    public void testGetValue() {
        LocalDateTimeRange ts0 = LocalDateTimeRange.fromUntil( A, B );
        String s = ts0.toString();
        LocalDateTimeRange ts1 = new LocalDateTimeRange(now(), now().plusDays(2));
        ts1 = ts1.setFromString( s );

        assertThat( ts1 ).isEqualTo( ts0 );

//        Assert.fail( "method testGetValue reached end. You know what to do." );
    }

//    @Ignore( "Think TDD" )
    @Test
    public void negativeDurationStartEarlier() {
        LocalDateTimeRange ts0 = new LocalDateTimeRange( B, Duration
                .of( -30, MINUTES ) );

        assertThat( ts0.getStart() ).as( "should produce test value A" ).isEqualTo( A );
//        Assert.fail( "method negativeDurationStartEarlier reached end. You know what to do." );
    }

//    @Ignore( "Think TDD" )
    @Test
    public void testBefore() {
        LocalDateTimeRange ts0 = new LocalDateTimeRange( A, Duration
                .of( -30, MINUTES ) );
        System.out.println( "ts0 = " + ts0 );
        assertThat( ts0.isBefore( A ) ).isTrue();
        assertThat( ts0.isBefore( A.plusMinutes( -5 ) ) ).isFalse();
//        Assert.fail( "method testIsAfter reached end. You know what to do." );
    }

//    @Ignore( "Think TDD" )
    @Test
    public void testAfter() {
        LocalDateTimeRange ts0 = new LocalDateTimeRange( A, B );//Duration.of( -30, MINUTES ) );
        System.out.println( "ts0 = " + ts0 );
        assertThat( ts0.isAfter( A ) ).isTrue();
        assertThat( ts0.isAfter( A.plusDays( 1 ) ) ).isFalse();
//        Assert.fail( "method testIsAfter reached end. You know what to do." );
    }

}
//End Solution
