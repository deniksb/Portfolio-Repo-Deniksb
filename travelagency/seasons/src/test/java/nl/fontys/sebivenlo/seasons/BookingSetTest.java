/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.sebivenlo.seasons;

import entities.Booking;
import entities.Room;
import java.time.LocalDate;
import static java.time.LocalDate.of;
import java.time.Period;
import static java.time.Period.ofDays;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import static java.util.stream.Collectors.toList;
import nl.fontys.sebivenlo.ranges.LocalDateRange;
import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class BookingSetTest {

    BookingSet bookingSet;
    LocalDateRange year;

    @BeforeEach
    void setup() {
        bookingSet = new BookingSet( 2020 );
//        assertThat( bookingSet.firstRange() ).isSameAs( bookingSet.lastRange() );
    }

    @Test
    public void startWithEmptyRange() {
//        assertThat( bookingSet.size() ).isEqualTo( 0 );
    }

    static Room roomWithView = new Room( "room with a view", "R100", 2, 100L,
            123 );

    /**
     * Test availability. Assert that any booking of an asset within the year is
     * available, when the booking set is fresh (and empty), and that once booked,
     * the time slot is no longer available.
     * 
     * In softly, assert that
     * <ol>
     * <li>asset is available.
     * </ol>
     */
    @Test
    public void availability() {

        LocalDateRange when = new LocalDateRange( of( 2020, 2, 1 ), of( 2020, 2,
                5 ) );
        Booking b = new Booking( roomWithView, "hallo", "piet@puk.org", when );
//        SoftAssertions.assertSoftly( softly -> {
//            softly.assertThat( bookingSet.isAvailable( when ) ).isTrue();
//            softly.assertThat( bookingSet.addBooking( b ) ).isTrue();
//            softly.assertThat( bookingSet.size() ).isEqualTo( 1 );
//            softly.assertThat( bookingSet.isAvailable( when ) ).isFalse();
//        } );
    }

    /**
     * Test values ar in ascending date order. a and a1 meet, as do d1 and d.
     */
    static Map<String, Supplier<Booking>> bookingTestMap = Map.of(
            "a", BookingSetTest::bsA,
            "a1", BookingSetTest::bsA1, // meets a
            "b", BookingSetTest::bsB,
            "c", BookingSetTest::bsC,
            "d1", BookingSetTest::bsd1, // meets d
            "d", BookingSetTest::bsD
    );

    /**
     * Check the setSize and the number of bookings (size).
     *
     * The internal 'structure' and invariant of the bookings set is
     * gap|Booking|gap|Booking, where two bookings can meet, but gaps cannot.
     * Assert that the size of the set matches the number of bookings and
     * the setSize matches the number of characters in the layout.
     * @param bookings to book
     * @param layout logical ordering of ranges in set. We only consider the length.
     */
    @ParameterizedTest
    @CsvSource( {
        "b,             gBg", // -B-
        "a|d,           BgB",// B-B
        "a|d|c,         BgBgB", // B-B-B
        "a|d1|d,        BgBB", // B-BB
        "a|a1|b|c|d|d1, BBgBgBgBB" // BB-B-B-BB
    } )
    public void addBooking( String bookings, String layout ) {
        final String[] parts = bookings.split( "\\|" );
        for ( String string : parts ) {
            bookingSet.addBooking( bookingTestMap.get( string.trim() ).get() );
        }
//        SoftAssertions.assertSoftly( softly -> {
//            //TODO B1A test addBooking
//            softly.fail("softly finished addBooking, you know what to do");
//        } );
    }

    /**
     * Check that not too little nor too much is removed. In particular, pay
     * attention to adjacent bookings, that meet on a day.
     *
     * @param bookString to map to actual bookings to fill first
     * @param delete the bookings to delete in the test
     * @param remaining the bookings that should remain.
     */
    @ParameterizedTest
    @CsvSource( {
        "a,a,", // delete only/first
        "b,b,", // delete in middle
        "a|b,a,b", // delete first
        //TODO B2A test remoboookings add more test cases for coverage
        
    } )
    public void removeBooking( String bookString, String delete,
            String remaining ) {

        for ( String string : bookString.split( "\\|" ) ) {
            bookingSet.addBooking( bookingTestMap.get( string.trim() ).get() );
        }

        System.out.println( "bookingSet = " + bookingSet );
        for ( String string : delete.split( "\\|" ) ) {
            bookingSet.
                    deleteBooking( bookingTestMap.get( string.trim() ).get() );
        }
        System.out.println( "bookingSet = " + bookingSet );
//        if ( remaining != null ) {
//            Booking[] remains = Arrays.stream( remaining.split( "\\|" ) )
//                    .map( s -> bookingTestMap.get( s.trim() ).get() ).
//                    collect( toList() )
//                    .toArray( new Booking[ 0 ] );
//            assertThat( bookingSet.stream() ).containsExactly( remains );
//        } else {
//            assertThat( bookingSet.stream() ).isEmpty();
//        }
    }

    static LocalDateRange ldr( LocalDate s, LocalDate e ) {
        return new LocalDateRange( s, e );
    }

    private static Booking bsA() {
        LocalDateRange jan = ldr( of( 2020, 1, 1 ), of( 2020, 1, 5 ) );
        Booking b1 = new Booking( roomWithView, "hallo", "piet1@puk.org", jan );
        return b1;
    }

    private static Booking bsA1() {
        LocalDateRange jan = ldr( of( 2020, 1, 5 ), of( 2020, 1, 8 ) );
        Booking b1 = new Booking( roomWithView, "hallo", "piet1a@puk.org", jan );
        return b1;
    }

    private static Booking bsB() {
        LocalDateRange feb = new LocalDateRange( of( 2020, 2, 1 ), ofDays( 4 ) );
        Booking b2 = new Booking( roomWithView, "hallo", "piet2@puk.org", feb );
        return b2;
    }

    private static Booking bsC() {
        LocalDateRange apr = new LocalDateRange( of( 2020, 4, 1 ), ofDays( 4 ) );
        Booking b3 = new Booking( roomWithView, "hallo", "piet3@puk.org", apr );
        return b3;
    }

    private static Booking bsd1() {
        LocalDateRange dec = ldr( of( 2020, 12, 24 ), of( 2020, 12, 28 ) );
        Booking b4 = new Booking( roomWithView, "hallo", "piet4@puk.org", dec );
        return b4;
    }

    private static Booking bsD() {
        LocalDateRange dec = ldr( of( 2020, 12, 28 ), of( 2021, 1, 1 ) );
        Booking b4 = new Booking( roomWithView, "hallo", "piet4@puk.org", dec );
        return b4;
    }

}
