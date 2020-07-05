//Start Solution::replacewith::
package nl.fontys.sebivenlo.ranges;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class IntegerRangeTest extends RangeTestBase<Integer, Integer,IntegerRange> {

    public IntegerRangeTest(){
        super( IntegerRange.class );
    }


    
    @Override
    Integer a() {
        return 42;
    }

    @Override
    Integer b() {
        return 51;
    }

    @Override
    Integer c() {
        return 55;
    }

    @Override
    Integer d() {
        return 1023;
    }
    @Override
    Integer e() {
        return 1610;
    }
    @Override
    Integer f() {
        return 2840;
    }

    @Override
    IntegerRange createRange( Integer start, Integer end ) {
        return new IntegerRange( start, end );
    }

    @Override
    Integer measurementUnit() {
        return 1;
    }

    @Test
    public void defaultUnit() {
        assertThat(new IntegerRange(1,2).defaultUnit()).isNotEqualTo(0);
    }

  
}
//End Solution
