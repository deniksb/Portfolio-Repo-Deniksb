package entities;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import nl.fontys.sebivenlo.ranges.LocalDateRange;
import nl.fontys.sebivenlo.seasons.Asset;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class Booking extends LocalDateRange {

    final Asset asset;
    final String description;
    final String clientEmail;

    public Booking( Asset asset, String description, String clientEmail, LocalDate start, LocalDate end ) {
        super( start, end );
        this.asset = asset;
        this.description = description;
        this.clientEmail = clientEmail;
    }

    public Booking( Asset asset, String description, String clientEmail, LocalDateRange when ) {
        this( asset, description, clientEmail, when.getStart(), when.getEnd() );
    }

    @Override
    public String toString() {
        return super.toString()+" descr=" + description +
                ", client=" + clientEmail;
    }
    public Asset getAsset() {
        return asset;
    }
   
}
