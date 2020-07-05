//Start Solution
package nl.fontys.sebivenlo.ranges;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class LongRange implements Range<LongRange,Long, Long> {

    final Long start;
    final Long end;

    public LongRange( long start, long end ) {
        this( Range.minmax( start, end ) );
    }

    private LongRange( Long[] l ) {
        this.start = l[ 0 ];
        this.end = l[ 1 ];
    }

    @Override
    public Long getStart() {
        return start;
    }

    @Override
    public Long getEnd() {
        return end;
    }

    @Override
    public MeasureBetween<Long, Long> meter() {
        return ( a, b, u ) -> b - a;
    }

    @Override
    public Long defaultUnit() {
        return 1L;
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
    public LongRange between( Long start, Long end ) {
        return new LongRange(start,end);
    }

    
}
//End Solution
