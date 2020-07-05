package entities;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import nl.fontys.sebivenlo.seasons.Asset;

/**
 * Potential candidate for Java 14 record.
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class Room implements Asset {

    final String description;
    final String shortName;
    final int capacity; // number of persons 
    final long pricePerDay;
    final int roomid;

    public Room( String description, String shortName, int capacity,
            long pricePerDay, int roomid ) {
        this.description = description;
        this.shortName = shortName;
        this.capacity = capacity;
        this.pricePerDay = pricePerDay;
        this.roomid = roomid;
    }


    @Override
    public long pricePerDay() {
        return pricePerDay;

    }

    @Override
    public String toString() {
        return "Room{" + "description=" + description + ", shortName="
                + shortName + ", pricePerDay=" + pricePerDay + ", roomid="
                + roomid + '}';
    }

    static AtomicInteger roomNumbers = new AtomicInteger();

    public static Room newRoom( String[] description ) {
        System.out.println( "description = " + Arrays.toString( description ) );
        return new Room( description[ 0 ], description[ 1 ], Integer.parseInt(description[2].trim()),Long.parseLong( 
                description[ 3 ].trim() ), roomNumbers.incrementAndGet() );
    }

    public int getCapacity() {
        return capacity;
    }
}
