package nl.fontys.sebivenlo.seasons;

import java.io.Serializable;

/**
 * An asset has a price per day.
 * 
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public interface Asset extends Serializable {
    long pricePerDay();
}
