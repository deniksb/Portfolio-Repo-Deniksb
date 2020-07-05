//Start Solution

package nl.fontys.sebivenlo.ranges;


/**
 * Simple integer based range.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class IntegerRange implements Range<IntegerRange, Integer, Integer> {

    final Integer start;
    final Integer end;

    public IntegerRange( Integer start, Integer end ) {
        this( Range.minmax( start, end ) );
    }

    private IntegerRange( Integer[] p ) {
        this.start = p[ 0 ];
        this.end = p[ 1 ];
    }

    @Override
    public Integer getStart() {
        return start;
    }

    @Override
    public Integer getEnd() {
        return end;
    }

    @Override
    public MeasureBetween<Integer, Integer> meter() {
        return ( a, b, u ) -> b - a;
    }

    @Override
    public Integer defaultUnit() {
        return 1;
    }

    @Override
    public int hashCode() {
        return rangeHashCode();
    }

    @Override
    @SuppressWarnings( "EqualsWhichDoesntCheckParameterClass" )
    public boolean equals( Object obj ) {
        return rangeEquals( obj );
    }

    @Override
    public String toString() {
        return rangeToString();
    }

    @Override
    public IntegerRange between( Integer start, Integer end ) {
        return new IntegerRange( start, end );
    }

}
//End Solution
