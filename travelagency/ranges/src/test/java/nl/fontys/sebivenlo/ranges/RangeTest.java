//Start Solution::replacewith::
package nl.fontys.sebivenlo.ranges;

import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

/**
 * Test the Range interface;
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class RangeTest extends RangeTestBase<Integer, Integer, IntegerRange> {

    static final int A = 10_000;
    static final int B = 22_000;
    static final int C = 35_000;
    static final int D = 47_000;
    static final int E = 128_000;
    static final int F = 256_000;

    public RangeTest(){
        super( IntegerRange.class );
    }

    @Test
    public void maxReturnsBOnEqual() {
        Integer a = a();
        Integer b = a();
        assertThat( Range.max( a, b ) ).isSameAs( a );
//        fail( "method maxReturnsAOnEqual reached end. You know what to do." );
    }

    @Test
    public void minReturnsBOnEqual() {
        Integer a = c();
        Integer b = c();
        assertThat( Range.min( a, b ) ).isSameAs( a );

//        fail( "method maxReturnsAOnEqual reached end. You know what to do." );
    }

    //Disabled("Think TDD")
    @Test
    public void minmaxTest() {
        Integer a = a();
        Integer b = b();
        Integer bb = b();
        Integer c = c();
        Integer d = d();
        Integer[] t = Range.minmax( a, b );
        Integer[] u = Range.minmax( d, c );
        Integer[] v = Range.minmax( bb, b );
        SoftAssertions.assertSoftly( softly -> {
            softly.assertThat( t[ 0 ] ).isSameAs( a );
            softly.assertThat( t[ 1 ] ).isSameAs( b );
            softly.assertThat( u[ 0 ] ).isSameAs( c );
            softly.assertThat( u[ 1 ] ).isSameAs( d );
            softly.assertThat( v[ 0 ] ).isSameAs( bb );
        } );

//        fail( "method minmaxTest reached end. You know what to do." );
    }

    @Test
    public void meets() {
        Integer a = a();
        Integer b = b();
        Integer bb = b();
        Integer c = c();
        Integer d = d();
        IntegerRange r1 = new IntegerRange( a, b );
        IntegerRange r2 = new IntegerRange( b, c );
        IntegerRange r3 = new IntegerRange( c, d );
        assertThat( r1.meets( r2 ) ).isTrue();
        assertThat( r2.meets( r1 ) ).isTrue();
        assertThat( r1.meets( r3 ) ).isFalse();
        assertThat( r3.meets( r1 ) ).isFalse();
//        fail( "method meets reached end. You know what to do." );
    }

    @Override
    Integer a() {
        return A;
    }

    @Override
    Integer b() {
        return B;
    }

    @Override
    Integer c() {
        return C;
    }

    @Override
    Integer d() {
        return D;
    }

    @Override
    Integer e() {
        return E;
    }

    @Override
    Integer f() {
        return F;
    }

    @Override
    IntegerRange createRange( Integer start, Integer end ) {
        return new IntegerRange( start, end );
    }

    @Override
    Integer measurementUnit() {
        return 1;
    }

}
//End Solution
