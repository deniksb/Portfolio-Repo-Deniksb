/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.sebivenlo.seasons;

import entities.Booking;
import entities.Room;
import entities.Student;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import static java.time.LocalDate.of;
import java.time.temporal.TemporalAdjusters;
import nl.fontys.sebivenlo.csvobjectstream.CSVObjectStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import nl.fontys.sebivenlo.ranges.LocalDateRange;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class TravelAgency {

    Map<String, Bookings> hotels;
    final String storageName = "hotels.ser";

    public TravelAgency() throws IOException {
        this.hotels = new HashMap<>();
        if ( Files.exists( Paths.get( this.storageName ) ) ) {
            System.out.println( "loaded " + storageName );
            this.load( this.storageName );
        }
        Thread saveThread = new Thread( () -> persistToDisk() );
        Runtime.getRuntime().addShutdownHook( saveThread );
    }

    void addHotel( String name, String pricesFile, String assetsfile ) throws IOException {
        hotels.put( name, new Bookings( pricesFile, assetsfile ) );
    }

    public static void main( String[] args ) throws IOException {
        TravelAgency travail = new TravelAgency();
        if ( travail.hotels.isEmpty() ) {
            travail.
                    addHotel( "majesticHotel", "seasonPrices.csv", "assets.csv" );
            travail.
                    addHotel( "fullham", "seasonPrices.csv", "fullhamAssets.csv" );
        }
        long price = travail.getPrice( "majesticHotel", 2, of( 2020, 1, 1 ),
                of( 2021, 1, 1 ) );
        System.out.println( "price per Year = " + formatPrice( price ) );

        LocalDate nextDay = LocalDate.now().plusDays( 1 );
        LocalDate end = nextDay.plusDays( 5 );
        long homPrice = travail.getPrice( "majesticHotel", 5, nextDay, end );
        System.out.println( "homPrice = " + homPrice );
        long studentPrice = travail.getPrice( "fullham", 1, nextDay, end );
        System.out.println( "studentPrice = " + studentPrice );
        travail.book( "majesticHotel", 1, nextDay, end, "Java conference",
                "p.vandenhombergh@gmail" );
        Path studentFilePath = Paths.get( "unseenstudents.csv" );

        boolean exists = Files.exists( studentFilePath );
        System.out.println( "exists = " + exists );
        Files.lines( studentFilePath ).forEach( System.out::println );
        CSVObjectStream<Student> studentReader = new CSVObjectStream<>(
                studentFilePath, "," );
        List<Student> students = studentReader.stream( Student::fac,
                r -> r[ 0 ].matches( "^\\d+$" ) ).collect( toList() );

        final AtomicLong studentsBooked = new AtomicLong();

        students.forEach(
                student -> {
                    if ( travail.book( "fullham", 1, nextDay, end,
                            "Java conference",
                            student.getEmail() ) ) {
                        System.out.println( "student booked " + student );
                        studentsBooked.incrementAndGet();
                    }
                }
        );
        System.out.println( "travail.hotels = " + travail.hotels );
    }

    static String formatPrice( long p ) {
        return String.format( "%d.%02d", ( p / 100 ), ( p % 100 ) );
    }

    boolean book( String hotelName, int roomSize, LocalDate start, LocalDate end,
            String description, String emailClient ) {
        final Bookings hotel = hotels.get( hotelName );
        if ( hotel == null ) {
            return false;
        }
        LocalDateRange when = new LocalDateRange( start, end );
        Optional<Map.Entry<Asset, BookingSet>> firstAvialableRoom = hotel.
                getBookings().entrySet()
                .stream().filter( r -> ( (Room) r.getKey() ).getCapacity()
                == roomSize && r.getValue().isAvailable( when ) )
                .findFirst();
        if ( firstAvialableRoom.isPresent() ) {
            Asset r = firstAvialableRoom.get().getKey();
            Booking b = new Booking( r, description, emailClient, when );
            hotel.book( b );
            return true;
        } else {
            System.out.println( 
                     "send mail to"+emailClient
                            + ": "+ hotelName+ " has no room available for the period " + when
                    + " for a room for " + roomSize + " person"
                    + ( roomSize > 1 ? "s" : "" ) );
            return false;
        }
    }

    long getPrice( String hotelName, int roomSize, LocalDate start,
            LocalDate end ) {
        final Bookings hotel = hotels.get( hotelName );
        if ( hotel == null ) {
            System.out.println( "hotel " + hotelName
                    + " not found" );
            return 0L;
        }
        LocalDateRange when = new LocalDateRange( start, end );
        Optional<Asset> firstAvialableRoom = hotel.
                getBookings().entrySet()
                .stream().filter( r -> ( (Room) r.getKey() ).getCapacity()
                == roomSize && r.getValue().isAvailable( when ) )
                .map( Entry::getKey )
                .findFirst();
        if ( firstAvialableRoom.isEmpty() ) {
            System.out.println( "no room found that matches" );
            return 0L;
        } else {
            return hotel.computePrice( firstAvialableRoom.get(), when );
        }
    }

    private void persistToDisk() {
        if ( hotels.isEmpty() ) {
            return; // nothing to do
        }
        try (
                 ObjectOutputStream out = new ObjectOutputStream(
                        new FileOutputStream( "hotels.ser" ) ); ) {
            out.writeObject( this.hotels );
        } catch ( FileNotFoundException ex ) {
            Logger.getLogger( getClass().getName() )
                    .log( Level.SEVERE, null, ex );
        } catch ( IOException ex ) {
            Logger.getLogger( getClass().getName() )
                    .log( Level.SEVERE, null, ex );
        }
    }

    private void load( String aStorageName ) {
        try (  ObjectInputStream in = new ObjectInputStream(
                new FileInputStream( aStorageName ) ) ) {
            this.hotels.clear();
            Map<String, Bookings> readMap = (Map<String, Bookings>) in.
                    readObject();

            this.hotels.putAll( readMap );

        } catch ( FileNotFoundException ex ) {
            Logger.getLogger( getClass().getName() ).
                    log( Level.SEVERE, null, ex );
        } catch ( IOException | ClassNotFoundException ex ) {
            Logger.getLogger( getClass().getName() ).
                    log( Level.SEVERE, null, ex );
        }

    }

    @Override
    public String toString() {
        for ( Entry<String, Bookings> hotel : hotels.entrySet() ) {
            System.out.println( "hotel " + hotel.getKey() );
            for ( Entry<Asset, BookingSet> assetEntry : hotel.getValue().
                    getBookings().entrySet() ) {
                System.out.println( " room = " + assetEntry.getKey() );
                System.out.println( "   bookings = " + assetEntry.getValue() );
                System.out.println( " end room" );
            }
            System.out.println( "end hotel" + hotel.getKey() );
        }

        return "TravelAgency{" + "hotels=" + hotels + '}';
    }

}
