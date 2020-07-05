package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import nl.fontys.sebivenlo.ranges.LocalDateRange;
import nl.fontys.sebivenlo.seasons.Asset;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class Season implements Serializable {
    
    private final String name;
    private final LocalDateRange dateRange;
    private final PriceLevel priceLevel;
    
    public Season( String name, LocalDateRange dateRange, PriceLevel priceLevel ) {
        this.name = name;
        this.dateRange = dateRange;
        this.priceLevel = priceLevel;
    }
    
    public Season( String name, LocalDate start, LocalDate end, PriceLevel priceClas ) {
        this( name, new LocalDateRange( start, end ), priceClas );
    }
    
    boolean contains( LocalDate d ) {
        return dateRange.contains( d );
    }
    
    LocalDate lastStay() {
        
        return dateRange.getEnd().minusDays( 1 );
    }
    
    public String getName() {
        return name;
    }
    
    public LocalDateRange getDateRange() {
        return dateRange;
    }
    
    public PriceLevel getPriceLevel() {
        return priceLevel;
    }
    
    @Override
    public String toString() {
        return "Season{" + "name=" + name + ", dateRange=" + dateRange + ", priceLevel=" + priceLevel + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode( this.dateRange );
        return hash;
    }
    
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Season other = ( Season ) obj;
        if ( !Objects.equals( this.dateRange, other.dateRange ) ) {
            return false;
        }
        return true;
    }

    /**
     * Create a season from array of string.
     * @param input array to construct season from csv record.
     * @return new season.
     */
    public static Season season( String[] input ) {
        String descr = input[ 0 ];
        LocalDate start = LocalDate.parse( input[ 1 ] );
        LocalDate end = LocalDate.parse( input[ 2 ] );
        PriceLevel pl = PriceLevel.valueOf( input[ 3 ] );
        return new Season( descr, new LocalDateRange( start, end ), pl );
    }
    
    /**
     * Can be used as ToLongFunction in the places where a lambda could be used.
     * @param a asset
     * @return the price in this season
     */
    public long getDayPrice( Asset a ) {
        return this.priceLevel.applyAsLong( a.pricePerDay() );
    }
}
