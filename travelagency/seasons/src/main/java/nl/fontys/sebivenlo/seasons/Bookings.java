package nl.fontys.sebivenlo.seasons;

import entities.Booking;
import entities.Room;
import entities.Season;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.time.LocalDate;
import static java.time.LocalDate.of;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;
import nl.fontys.sebivenlo.csvobjectstream.CSVObjectStream;
import nl.fontys.sebivenlo.ranges.LocalDateRange;

/**
 * Core of a booking travel agency.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class Bookings implements Serializable {

    final Map<LocalDateRange, Season> seasonMap
            = new LinkedHashMap<>();
    private Map<Asset, BookingSet> bookings;

    final static int THIS_YEAR = LocalDate.now().getYear();
    static LocalDate janFirst = of( THIS_YEAR, 1, 1 );
    static LocalDate janFirstNextYear = of( THIS_YEAR + 1, 1, 1 );

    /**
     * Read the season prices from a default file.
     *
     * @param seasonPrices to read
     * @param assetsFile to read
     * @throws IOException on fnf
     */
    Bookings( String seasonPrices, String assetsFile ) throws IOException {
        new CSVObjectStream<Season>( Paths.get( seasonPrices ), ",", s -> !s.
                startsWith( "#" ) )
                .stream( Season::season, r -> r[ 1 ].matches(
                "^\\d{4}-\\d{2}-\\d{2}$" ) )
                .forEach( s -> {
                    seasonMap.put( s.getDateRange(), s );
                } );
        bookings = new LinkedHashMap<>(); // keeps things in insertion order.
        new CSVObjectStream<Room>( Paths.get( assetsFile ), ",", s -> !s.
                startsWith( "#" ) )
                .stream( Room::newRoom, r -> true )
                .forEach( ( Room s ) -> {
                    BookingSet bookingSet = new BookingSet( 2020 );
                    this.bookings.put( s, bookingSet );
                } );
        bookings = Collections.synchronizedMap( bookings );
    }

    /**
     * Read the season prices from the default file.
     *
     * @throws IOException on fnf
     */
    public Bookings() throws IOException {
        this( "./seasonPrices.csv", "assets.csv" );
    }

    /**
     * Test if an Asset is available in a given period.
     *
     * @param asset to check for
     * @param when period to check
     * @return the result of the check
     */
    boolean isAvailable( Asset asset, LocalDateRange when ) {
        BookingSet forAsset = bookings.get( asset );
        if ( forAsset == null ) {
            return false;
        }
        return forAsset.isAvailable( when );
    }

    /**
     * Return an unmodifiable map for inspection by e.g. tests.
     *
     * @return the current bookings
     */
    Map<Asset, BookingSet> getBookings() {
        return Collections.unmodifiableMap( bookings );
    }

    @Override
    public String toString() {
        return " bookings {"+bookings.entrySet()
                .stream()
                .filter( e -> e.getValue().size() > 0 )
                .map( e -> e.getKey() + " " + e.getValue() )
                .collect( joining( System.lineSeparator() ) );
    }

    /**
     * Compute the price of an Asset during a given period.
     *
     * @param asset to price
     * @param when the period of rent
     * @return the price of the asset over the whole period
     */
    long computePrice( Asset asset, LocalDateRange when ) {
        //TODO B3B compute price of an asset over a period
        return 0L;
    }

    /**
     * Book an Asset for the given period, making it unavailable during that
     * period.
     *
     * Asset and period, as well as customer details are provided by the booking
     * object, which is a subclass of LocalDateRange.
     *
     * @param b the booking details, such as period, customer, and Asset
     * @return true if the booking was successful.
     */
    boolean book( Booking b ) {
        //TODO B4B add a booking for an asset.
        return false;
    }

    /**
     * Cancel a booking.
     *
     * @param b the booking
     * @return true if the bookings for the asset changed.
     */
    boolean cancel( Booking b ) {
        //TODO B5B cancel the reservation for the asset of the booking
        return false;
    }

    Stream<Room> getRooms() {
        return bookings.keySet()
                .stream()
                .filter( r -> r instanceof Room )
                .map( Room.class::cast );
    }
}
