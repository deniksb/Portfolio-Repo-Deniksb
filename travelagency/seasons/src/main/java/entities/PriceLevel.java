package entities;

import java.util.function.LongUnaryOperator;

/**
 * Price levels.
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 *
 */
public enum PriceLevel implements LongUnaryOperator {
    LOW( 60 ), REGULAR( 100 ), WEEKEND( 120 ), HIGH( 160 );

    private PriceLevel( long percent ) {
        this.percent = percent;
    }

    private long percent;

    @Override
    public long applyAsLong( long arg ) {
        return ( percent * arg ) / 100L;
    }
}
