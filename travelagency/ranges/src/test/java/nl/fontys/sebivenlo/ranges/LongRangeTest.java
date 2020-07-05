//Start Solution::replacewith::
package nl.fontys.sebivenlo.ranges;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class LongRangeTest extends RangeTestBase<Long, Long, LongRange> {

    LongRangeTest() {
        super( LongRange.class );
    }

    @Override
    Long a() {
        return 420_000L;
    }

    @Override
    Long b() {
        return 510_000L;
    }

    @Override
    Long c() {
        return 550_000L;
    }

    @Override
    Long d() {
        return 10_230_000L;
    }

    @Override
    Long e() {
        return 20_230_000L;
    }

    @Override
    Long f() {
        return 30_230_000L;
    }

    @Override
    LongRange createRange( Long start, Long end ) {
        return new LongRange( start, end );
    }

    @Override
    Long measurementUnit() {
        return 1l;
    }

    @Test
    public void defaultUnit() {
        assertThat( new LongRange( 10L, 20L ).defaultUnit() ).isNotEqualTo( 0L );
    }

}
//End Solution
