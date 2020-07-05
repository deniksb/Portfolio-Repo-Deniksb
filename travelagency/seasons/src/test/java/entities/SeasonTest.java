/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.PriceLevel;
import entities.Season;
import static java.time.LocalDate.now;
import static java.time.LocalDate.of;
import nl.fontys.sebivenlo.ranges.LocalDateRange;
import nl.fontys.sebivenlo.seasons.Asset;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class SeasonTest {

    @ParameterizedTest
    @CsvSource( {
        "REGULAR,1000",
        "LOW,600",
        "HIGH,1600",
        "WEEKEND,1200"
    } )

    public void getAssetPrice( String levelName, long expected ) {
        Asset asset = new Asset() {
            @Override
            public long pricePerDay() {
                return 1000;
            }
        };

        Season ses = new Season( "Test", 
                new LocalDateRange( now( ), now().plusDays(2) ), 
                PriceLevel.valueOf( levelName) );

        long actual = ses.getDayPrice( asset );
        assertThat( actual ).isEqualTo( expected );
    }
}
