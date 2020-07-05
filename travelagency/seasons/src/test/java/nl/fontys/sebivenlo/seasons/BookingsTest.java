package nl.fontys.sebivenlo.seasons;

import entities.Booking;
import entities.Room;
import java.io.IOException;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import static java.time.LocalDate.of;
import java.time.Period;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.*;
import nl.fontys.sebivenlo.ranges.LocalDateRange;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class BookingsTest {

    Bookings bookings;

    public BookingsTest() throws IOException {
        this.bookings = new Bookings();
    }

    @Test
    void canBook() {
        Asset room = bookings.getBookings().keySet().stream().findFirst().get();
        System.out.println( "room = " + room );
        LocalDateRange when = new LocalDateRange( of( 2020, 2, 28 ), Period.
                ofDays( 2 ) );
        Booking b = new Booking( room, "description", "p.puk@gmail.com", when );
        boolean canDo = bookings.isAvailable( room, when );
        System.out.println( "canDo = " + canDo );

        boolean booked = bookings.book( b );
//        SoftAssertions.assertSoftly( softly -> {
//            softly.assertThat( booked ).isTrue();
//            softly.assertThat( bookings.isAvailable( room, when ) ).isFalse();
//        } );

        System.out.println( "bookings=" + bookings );
//        fail( "method canBook reached end. You know what to do." );
    }

    /**
     * Test compute Price.
     *
     * @param start
     * @param length
     * @param expected
     */
    @ParameterizedTest
    @CsvSource( {
        "2020-02-28,2,12000",
        "2020-02-28,5,22800",
        "2020-02-01,21,92400",
        "2020-06-27,21,126000", //
    } )
    void computePrice( LocalDate start, int length, long expected ) {
        Asset room = bookings.getBookings().keySet().stream().findFirst().get();
        LocalDateRange when = new LocalDateRange( start, Period.ofDays( length ) );
        long price = bookings.computePrice( room, when );
        //TODO B3A test compute price, write assertion
//        fail("test computePrice reached end. You know what to do.");
    }

    /**
     * Try to insert two overlapping bookings for same asset. Only the first should survive.
     */
    @Test
    public void book() {
        // get the first available room
        Asset room = bookings.getBookings().keySet().stream().findFirst().get();

        // ranges when and when2 overlap
        LocalDateRange when = new LocalDateRange( now(), Period.ofDays( 5 ) );
        LocalDateRange when2 = new LocalDateRange( now().plusDays( 1 ), Period.
                ofDays( 5 ) );

        Booking firstBooking = new Booking( room, "vakantie", "piet.puk@gmail.com",
                when );
        Booking secondBooking = new Booking( room, "vakantie",
                "Heintje.Davids@gmail.com", when2 );
        //TODO B4A test that book succeeds for first only
//        fail( "method book reached end. You know what to do." );
    }

    /**
     * Test the result for a room that does not exist in the assets.
     */
    @Test
    public void isAvaiableNot() {
        Asset room = new Room( "washok", "do not enter", 0, 0, 0 );
        LocalDateRange when = new LocalDateRange( now(), Period.ofDays( 5 ) );
        assertThat( bookings.isAvailable( room, when ) ).isFalse();

//        fail( "method isAvaiableNot reached end. You know what to do." );
    }

    @Test
    public void getRooms() throws IOException {
        Bookings b = new Bookings();
        List<Room> rooms = b.getRooms().collect( toList() );
        assertThat( rooms ).hasSize( 29 );
//        fail( "method getRooms reached end. You know what to do." );
    }

    @Test public void getBookingsForRoom() {
        Asset room = bookings.getBookings().keySet().stream().findFirst().get();
        LocalDateRange when = new LocalDateRange( now(), Period.ofDays( 5 ) );
        LocalDateRange when2 = new LocalDateRange( now().plusDays( 5 ), Period.
                ofDays( 5 ) );
        Booking booking = new Booking( room, "vakantie", "piet.puk@gmail.com",
                when );
        Booking booking2 = new Booking( room, "vakantie",
                "Heintje.Davids@gmail.com", when2 );
        bookings.book( booking );
        bookings.book( booking2 );
        BookingSet roomBookings = bookings.getBookings().get( room );
//        assertThat( roomBookings.size() ).isEqualTo( 2 );

//        fail( "method getBookings reached end. You know what to do." );
    }

    /**
     * Place two bookings head to tail, remove the first en assert that the
     * other remains.
     *
     */
    @Test public void cancelBooking() {
        Asset room = bookings.getBookings().keySet().stream().findFirst().get();

        LocalDateRange when = new LocalDateRange( now(), Period.ofDays( 5 ) );
        LocalDateRange when2 = new LocalDateRange( now().plusDays( 5 ), Period.
                ofDays( 5 ) );
        Booking booking = new Booking( room, "vakantie", "piet.puk@gmail.com",
                when );
        Booking booking2 = new Booking( room, "vakantie",
                "Heintje.Davids@gmail.com", when2 );
        bookings.book( booking );
        bookings.book( booking2 );

        //TODO B5A test cancelBooking
//        fail( "method getBookings reached end. You know what to do." );
    }

    //@Disabled("Think TDD")
    @Test
    public void toStringTest() {
        assertThat( bookings.toString() ).isNotEmpty();//
        Asset room = bookings.getBookings().keySet().stream().findFirst().get();
        LocalDateRange when = new LocalDateRange( now(), Period.ofDays( 5 ) );
        Booking booking = new Booking( room, "vakantie", "piet.puk@gmail.com",
                when );
//        assertThat( bookings.book( booking ) ).isTrue();
//        assertThat( bookings.toString() ).contains( "" + now().getYear() );
        //contains("2020"); 
//        fail( "method toStringTest reached end. You know what to do." );
    }
}
