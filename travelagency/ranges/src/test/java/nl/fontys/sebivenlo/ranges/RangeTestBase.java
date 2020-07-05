package nl.fontys.sebivenlo.ranges;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import static java.time.ZoneId.systemDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 * @param <T> demarcation type of range
 * @param <U> unit of distance
 * @param <R> range type
 */
//@RunWith( Parameterized.class )
public abstract class RangeTestBase<T extends Comparable<? super T>, U extends Comparable<? super U>, R extends Range> {

    private final Class<R> typeToken;

    /**
     * Remember the type of the class under test.
     *
     * @param typeToken for this class.
     */
    public RangeTestBase( Class<R> typeToken ) {
        this.typeToken = typeToken;
    }

    /**
     * Create a test point.
     *
     * @return test point a
     */
    abstract T a();

    /**
     * Create a test point.
     *
     * @return test point a
     */
    abstract T b();

    /**
     * Create a test point.
     *
     * @return test point a
     */
    abstract T c();

    /**
     * Create a test point.
     *
     * @return test point a
     */
    abstract T d();

    /**
     * Create a test point.
     *
     * @return test point a
     */
    abstract T e();

    /**
     * Create a test point.
     *
     * @return test point a
     */
    abstract T f();

    /**
     * Create a test range
     *
     * @param start or range
     * @param end of range
     * @return the range
     */
    abstract R createRange( T start, T end );

    /**
     * Get the unit of measurement.
     *
     * @return the unit
     */
    abstract U measurementUnit();

    /**
     * Map to translate characters to suppliers.
     */
    Map<Character, Supplier<T>> abcdSup = Map.of(
            'a', () -> a(),
            'b', () -> b(),
            'c', () -> c(),
            'd', () -> d(),
            'e', () -> e(),
            'f', () -> f()
    );

    /**
     * Helper method to get from a two char string to list of points. The method
     * uses the lookup map to get a supplier of points that returns the actual
     * points.
     *
     * @param label specifying the point
     * @return the points as list
     */
    List<T> pointList( String label ) {
        List<T> pt2 = new ArrayList<>();
        char[] abcd = label.toCharArray();
        for ( char i : abcd ) {
            pt2.add( abcdSup.get( i ).get() );
        }
        return pt2;
    }

    /**
     * Helper to create a range from spec.
     *
     * @param spec of range, e.g bc = Range [b,c).
     * @return the range
     */
    R createRange( String spec ) {
        List<T> p1 = pointList( spec );
        return createRange( p1.get( 0 ), p1.get( 1 ) );
    }

    /**
     * Test the overlaps function. The method is given. Add more test values.
     *
     * @param rp1 point pair 1
     * @param rp2 point pair 2
     * @param overlaps expected outcome
     */
//    @Disabled
    @ParameterizedTest
    @CsvSource( value = {
        "ab,cd,false", // disjunct
        "ac,cd,false", // meet
        "ac,bd,true", //  B < C < D
        //TODO A1A test overlaps() add more test values to improve coverage
    
    }
    )
    void overlaps( String rp1, String rp2, boolean overlaps ) {
        Range r1 = createRange( rp1 );
        Range r2 = createRange( rp2 );
        assertThat( r1.overlaps( r2 ) )
                .as( rp1 + "+" + rp2 )
                .isEqualTo( overlaps );
    }

    /**
     * Compute the overlap function as long.
     *
     * @param rp1 point pair one defining first range
     * @param rp2 point pair two defining second range
     * @param rp3 point pair with expected length
     */
//    @Disabled( "Think TDD" )
    @ParameterizedTest
    @CsvSource( value = {
        // first, second, distance  points
        "ab,cd,aa", // disjunct
        "ab,bc,bb", // disjunct
        "ac,bd,bc", //  B < C < D
        //TODO A2A test overlap(). add test valeus for coverage

    }
    )
    void overLap( String rp1, String rp2, String rp3 ) throws Exception {
        Range r1 = createRange( rp1 );
        Range r2 = createRange( rp2 );
        Range r3 = createRange( rp3 );
        long expectedOverlap = r3.getLength( r3.defaultUnit() );
        long actual = r1.overlap( r2, r1.defaultUnit() );
//        assertThat( actual ).as( rp1 + "+" + rp2 ).isEqualTo( expectedOverlap );
//        fail("test overLap completed, you know what to do.");
    }

//    @Disabled( "Think TDD" )
    @Test
    void testLength() {
        assertThat( createRange( a(), b() ).getLength( measurementUnit() ) > 0 ).
                isTrue();

//        Assert.fail( "hashCodeT testLength reached end. You know what to do." );
    }

//    @Disabled( "Think TDD" )
    @Test
    void normalizes() {
        assertThat( createRange( b(), a() ).getLength( measurementUnit() ) > 0 ).
                isTrue();
    }

    /**
     * Check the contain(p) method word correctly. Method is given. Add test
     * values.
     *
     * @param pp first range lookup
     * @param point to check
     * @param contains expected value
     */
//    @Disabled( "Think TDD" )
    @ParameterizedTest
    @CsvSource( {
        "bc,a,false",
        "bc,d,false",
        //TODO A3A test containsPoint(). add more test values to improve coverage
    
    } )
    void containsPoint( String pp, String point, boolean contains ) {
        // coverage
        R r = createRange( pp );
        T p = abcdSup.get( point.charAt( 0 ) ).get();
        assertThat( r.contains( p ) )
                .as( pp + " contains" + point + " " + contains )
                .isEqualTo( contains );
    }

    /**
     * Check the meter function. The meter is applied on a range and two points.
     * The test method is complete. Add test values.
     *
     * @param rp range
     * @param pp1 point 1
     * @param pp2 point 2
     * @param check value. Multiply with value to get the sign correct.
     */
    @ParameterizedTest
    @CsvSource( {
        "ab,a,b,1",
        "aa,a,a,0",
        "cd,d,c,-1", } )
    void meter( String rp, String pp1, String pp2, int check ) {
        R range = createRange( rp );
        T p1 = abcdSup.get( pp1.charAt( 0 ) ).get();
        T p2 = abcdSup.get( pp2.charAt( 0 ) ).get();

//        R r2 = createRange( d(), a() );
        Comparable defaultUnit = range.defaultUnit();
        long length = range.getLength( defaultUnit );
        long length2 = range.meter().applyAsLong( p1, p2, defaultUnit );
        //long length3 = r2.meter().applyAsLong( a(), d(), r2.defaultUnit() );
        SoftAssertions.assertSoftly( softly -> {
            if ( check == 0 ) {
                softly.assertThat( length ).isZero();
                softly.assertThat( length2 ).isZero();
            } else {
                softly.assertThat( length * check ).isEqualTo( length2 );
            }
        }
        );

//        fail( "hashCodeT hashCodeT reached end. You know what to do." );
    }

    @Test
    void equalsTest() {
        R r1 = createRange( b(), a() );
        R r2 = createRange( b(), c() );
//        assertThat( r1.rangeEquals( r2 ) ).isFalse();
//        fail( "hashCodeT equals reached end. You know what to do." );
    }

    @Test
    void toStringTest() {
        T b = b();
        R r1 = createRange( b(), a() );
//        assertThat( r1.toString() ).contains( b.toString() );
//        fail( "hashCodeT toStringTest reached end. You know what to do." );
    }

    @Test
    @SuppressWarnings( "ObjectEqualsNull" )
    void testEquals() {
        R r0 = createRange( "ab" );
        R r1 = createRange( "ab" );
        R r2 = createRange( "ac" );
        R r3 = createRange( "bc" );
        R nill = null;
//        assertThat( r0.equals( r0 ) ).isTrue();
//        assertThat( r0.equals( r1 ) ).isTrue();
//        assertThat( r0.equals( r2 ) ).isFalse();
//        assertThat( r0.equals( r3 ) ).isFalse();
//
//        assertThat( r0.equals( "string" ) ).isFalse();
//
//        assertThat( r0.equals( nill ) ).isFalse();

//        Assert.fail( "method testEquals reached end. You know what to do." );
    }

    @Test
    void hashCodeTest() {
        R r1 = createRange( b(), a() );
        R r2 = createRange( c(), d() );
        R r3 = createRange( b(), a() );
//        assertThat( r1.hashCode() ).isNotEqualTo( r2.hashCode() );
//        assertThat( r1.hashCode() ).isEqualTo( r3.hashCode() );
//        fail( "method method reached end. You know what to do." );
    }

    //    @Disabled( "Think TDD" )
    @Test
    void testSwappedBounds() {
        R r1 = createRange( b(), a() );
//        assertThat( r1.getEnd() ).isEqualTo( b() );
//        Assert.fail( "method method reached end. You know what to do." );
    }

    @ParameterizedTest
    @CsvSource( {
        "ab,bc,true",
        "ab,cd,false",
        "ac,bd,true"
    } )
    void checkMeetsOrOverlaps( String pp1, String pp2, boolean meetsOrOverLaps ) {
        R r1 = createRange( pp1 );
        R r2 = createRange( pp2 );
        // code that should throw or not throw exception.
        ThrowingCallable code = () -> {
            r1.checkMeetsOrOverlaps( r2 );
        };

//        if ( meetsOrOverLaps ) {
//            assertThatCode( code ).doesNotThrowAnyException();
//
//        } else {
//            assertThatThrownBy( code ).isInstanceOf(
//                    IllegalArgumentException.class );
//        }
//        fail( "method checkMeetsOrt reached end. You know what to do." );
    }

    /**
     * Check joinWith. The test values should all produce a join, the exception
     * throwing is tested elsewhere.
     *
     * @param pp1 first range spec
     * @param pp2 second range spec.
     * @param expectedRange in the test
     */
    @ParameterizedTest
    @CsvSource( {
        "ab,bc,ac",
        "ac,bd,ad"
    } )
    void joinWithABCD( String pp1, String pp2, String expectedRange ) {
        R r1 = createRange( pp1 );
        R r2 = createRange( pp2 );
        R expected = createRange( expectedRange );
        assertThat( r2.joinWith( r1 ) ).isEqualTo( expected );
//        fail( "method joinWith reached end. You know what to do." );
    }

    /**
     * Check the commonRange method between a range and a 'cutter'.
     *
     * Method is given, add test values.
     *
     * @param rp1 range specification
     * @param rp2 cutter range spec
     * @param cuts expected value 1
     * @param rp3 expected result of cut.
     */
    @ParameterizedTest
    @CsvSource( value = {
        // this, cutter, cuts, expected result
        "ab,cd,false,",
        "ab,bc,false,",
        "ac,bd,true,bc",
        //TODO A5A test commonRange add more test values to improve coverage
        
    }
    )
    void commonRange( String rp1, String rp2, boolean cuts, String rp3 ) {
        R range = createRange( rp1 );
        R cutter = createRange( rp2 );
        Optional<R> result = range.commonRange( cutter );
//        if ( cuts ) {
////            SoftAssertions.assertSoftly( softly -> {
////                R expected = createRange( rp3 );
////                softly.assertThat( result ).isNotEmpty();
////                R actual = result.get();
////                softly.assertThat( actual.rangeEquals( expected ) ).isTrue();
//            } );
//        } else {
////            assertThat( result ).isEmpty();
//        }

    }

    /**
     * Test if range is fully contained in other. (contains method)
     *
     * Method is given. Add test values
     *
     * @param rp1 this range
     * @param rp2 other range
     * @param expected outcome.
     */
    @ParameterizedTest
    @CsvSource( value = {
        // this, cutter, cuts, expected result
        "ab,cd,false", // disjunct
        //TODO A4A test containsRange. add more test values to improve coverage
    
    }
    )
    void containsRange( String rp1, String rp2, boolean expected ) {
        Range range = createRange( rp1 );
        Range other = createRange( rp2 );
//        assertThat( range.contains( other ) ).as( rp1 + " contains " + rp1 ).
//                isEqualTo( expected );

    }

    /**
     * Test the punchThrough method. Test is given. Add test values.
     *
     * In expected value ab|bc means a stream with exactly the elements [ab) and
     * [bc).
     *
     *
     * @param rangeP range value
     * @param punchP punch value
     * @param restPairs, | separated list of expected ranges in stream
     */
    @ParameterizedTest
    @CsvSource( value = {
        // range, punch, results, | separated
        "ab,ab,ab", // replace
        "ac,ab,ab|bc", // left punch
        //TODO A6A test punchThrough(...). add more test values to improve coverage
       
    }
    )
    void punchThrough( String rangeP, String punchP, String restPairs ) {
        R range = createRange( rangeP );
        R punch = createRange( punchP );
        var expectedParts = restRanges( "\\|", restPairs );
        var result = range.punchThrough( punch );
//        assertThat( result )
//                .as( "punch " + range + " with " + punch )
//                .containsExactlyElementsOf( expectedParts );
    }

    /**
     * Helper to turn a string into a list of pairs.
     *
     * @param splitAt split string.
     * @param restPairs string to be split
     * @return the list of pairs.
     */
    private List<R> restRanges( String splitAt, String restPairs ) {
        return Arrays.stream( restPairs.split( splitAt ) )
                .map( this::createRange )
                .collect( toList() );
    }

    /**
     * Test compareTo. The outcome is negative, zero or positive, which is
     * expressed in the table as -1, 0. or 1.
     *
     * To test for zero is easy, but a special case. To test for the negative
     * value, multiply expected with the actual value and test it to be greater
     * than 0.
     *
     * we need to detect that result and expected have the same // sign or are
     * equal. // we can achieve
     *
     * @param pp1 range 1
     * @param pp2 range 2
     * @param expected value
     */
    @ParameterizedTest
    @CsvSource( {
        "ab,ac,0", // same start
        "ac,bd,-1", // start left of
        "bc,ad,1", // start right of 
    } )
    void compareTo( String pp1, String pp2, int expected ) {
        R r1 = createRange( pp1 );
        R r2 = createRange( pp2 );

//        if ( expected == 0 ) {
//            assertThat( r1.compareTo( r2 ) ).isZero();
//        } else {
//            assertThat( r1.compareTo( r2 ) * expected ).isGreaterThan( 0 );
//        }
    }

    /**
     * Some range types need a configurable clock. To avoid generics
     * complexities and code duplication at the same time it is in this testBase
     * class, although not strictly needed for all range types.
     */
    static final Clock clock = new Clock() {
        @Override
        public ZoneId getZone() {
            return systemDefault();
        }

        @Override
        public Clock withZone( ZoneId zone ) {

            throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Instant instant() {
            return Instant.ofEpochSecond( 1557223200L );
        }
    };

}
