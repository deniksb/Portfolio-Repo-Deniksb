package nl.fontys.sebivenlo.ranges;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Objects;
import java.util.Optional;
import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

/**
 * The range's reason of existence is testing, measuring, and possibly avoiding
 * overlaps.
 *
 * <p>
 * The range should comply to the invariant that the start is less or equal it's
 * end. Depending on the type, this can mean start is (left of, before, lower)
 * than end, or equal to end.</p>
 *
 * <p>
 * This range definition is of the <i>half open</i> type. The start is included
 * in the range, the end is not. The mathematical notation for half open ranges
 * of this kind is [start,end), indeed with two different brackets. The square
 * bracket is the closed end, the parenthesis is at the open end.</p>
 *
 * <p>
 * The demarcation and distance units can be of different types, like a dates as
 * demarcation and days between them.</p>
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 * @param <R> Range, always needed in definition of implementing class since
 * this interface has a recursive generic definition.
 * @param <T> the demarcation type. Type of start and end.
 * @param <U> the distance unit. Like days between dates.
 */
public interface Range<R extends Range, T extends Comparable<? super T>, U extends Comparable<? super U>>
        extends Comparable<Range<R, T, U>>, Serializable {

    /**
     * Get the start demarcation of this range. Start is part of this range.
     *
     * @return start
     */
    T getStart();

    /**
     * Get the end demarcation of this range. All points in the range are before
     * (less than) the end of this range.
     *
     * @return end
     */
    T getEnd();

    /**
     * Is a point contained in this range.
     *
     * @param point the point
     * @return true is point not before start and not after end.
     */
    default boolean contains( T point ) {
        //TODO A3B implement contains( T point )
        if(point.compareTo(getStart()) >= 0){  //if it is equal or after the start date it is in the range
            if(point.compareTo(getEnd()) <= 0){  //if it is equal or before the end date it is in the range
                return true;
            }
        }
        return false;
    }

    /**
     * Does this range overlap with another one.
     *
     * @param other to check
     * @return true on overlap with other
     */
    default boolean overlaps( Range<R, T, U> other ) {
        //TODO A1B implement overlaps(...)
        if(other.getStart().compareTo(getStart()) > 0 && other.getStart().compareTo(getEnd()) < 0){ //if other.startdate is between the start and end date of this then it overlaps
                return true;
        }
        else if(other.getEnd().compareTo(getStart()) > 0 && other.getEnd().compareTo(getEnd()) < 0){  //if other.enddate is between the start and end date of this then it overlaps
            return true;
        }
        else if(other.getStart().compareTo(getStart()) == 0 && other.getEnd().compareTo(getEnd()) == 0) {    //if the start and end dates are equal then they overlap
            return true;
        }

        return false;
    }

    /**
     * Obvious maximum function to get the maximum or right most of two
     * elements.
     *
     * @param <Z> type of elements
     * @param a element a
     * @param b element b
     * @return the maximum of a and b
     */
    static <Z extends Comparable<? super Z>> Z max( Z a, Z b ) {
        return a.compareTo( b ) >= 0 ? a : b;
    }

    /**
     * Obvious minimum function to get the minimum or left most of two elements.
     *
     * @param <Z> type of elements
     * @param a element a
     * @param b element b
     * @return the minimum of a and b
     */
    static <Z extends Comparable<? super Z>> Z min( Z a, Z b ) {
        return a.compareTo( b ) <= 0 ? a : b;
    }

    /**
     * Get the length of this range in some unit. The effective operation is
     * (end - start), but since we do not know how to subtract the T-type, that
     * is left to the implementer. The exception thrown on incompatibility of
     * range and unit is also left to the implementer.
     *
     * @param unit of measurement
     * @return the length
     * @throws RuntimeException when the unit and this range are not compatible
     */
    default long getLength( U unit ) {
        return meter().applyAsLong( this.getStart(), this.getEnd(), unit );
    }

    /**
     * Compute the length that this and an other range overlap.
     *
     * Compute the distance between the left most end and the right most start.
     *
     *
     * @param other range
     * @param unit of measurement
     * @return the length of the overlap
     */
    default long overlap( Range<R, T, U> other, U unit ) throws Exception {
        //TODO A2B implement overlap()
        T leftMostEnd;
        T rightMostStart;
        if(getEnd().compareTo(other.getEnd()) > 0){          //deciding which is the left most end by comparing the two
            leftMostEnd = other.getEnd();
        }
        else {
            leftMostEnd = getEnd();
        }

        if(getStart().compareTo(other.getStart()) > 0){     // deciding which is the right most start by comparing the two
            rightMostStart = getStart();
        }
        else {
            rightMostStart = other.getStart();
        }

        if(unit.getClass().equals(ChronoUnit.class)){
            return ChronoUnit.DAYS.between((Temporal)leftMostEnd,(Temporal) rightMostStart); //
        }
        else if(unit.getClass().equals(Integer.class)){

                return (long) Math.abs((Integer) leftMostEnd - (Integer) rightMostStart);
        }
        else if(unit.getClass().equals(Long.class)){
                return Math.abs((Long) leftMostEnd - (Long) rightMostStart);
        }
        else {
            throw new Exception("Incompatible type!");
        }

    }

    /**
     * Does this and other just meet. this.start== other.end or
     * other.start=this.end
     *
     * @param other range
     * @return meet
     */
    default boolean meets( Range< R, T, U> other ) {
        return max( this.getStart(), other.getStart() ).equals( min( this.
                getEnd(), other.getEnd() ) );
    }

    /**
     * Helper to check that join is allowed.
     *
     * @param other to test
     * @throws IllegalArgumentException when overlap is not possible
     */
    default void checkMeetsOrOverlaps( Range<R, T, U> other ) {
        if ( !( meets( other ) || overlaps( other ) ) ) {
            throw new IllegalArgumentException( "this range " + this.toString()
                    + " and other " + other.toString()
                    + " do not meet nor overlap" );
        }

    }

    /**
     * Join this range with other range.
     *
     * @param other range to join
     * @return new joined range.
     * @throws IllegalArgumentException when this and other have no points in
     * common, in other words do not overlap or meet.
     */
    default R joinWith( Range<R, T, U> other ) throws IllegalArgumentException {
        checkMeetsOrOverlaps( other );
        return between( min( this.getStart(), other.getStart() ), max( this.
                getEnd(), other.getEnd() ) );
    }

    /**
     * Get the method to determine distances between points.
     *
     * @return a function to compute the distance from a to b.
     */
    MeasureBetween<T, U> meter();

    /**
     * Get the default distance unit of this range.
     *
     * @return the unit
     */
    U defaultUnit();

    /**
     * Helper to avoid code duplication.
     *
     * @return the hash code for this object
     */
    default int rangeHashCode() {
        return Objects.hash( getStart(), getEnd() );
    }

    /**
     * Helper equals. This implementation only tests for equality of the
     * boundary points. Use it to compare a Range instance and a subclass
     * instance.
     *
     * @param obj other to compare
     * @return the truth about
     */
    default boolean rangeEquals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( this == obj ) {
            return true;
        }
        if ( !( obj instanceof Range ) ) {
            return false;
        }
        Range other = Range.class.cast( obj );
        return Objects.equals( this.getStart(), other.getStart() ) && Objects.
                equals( this.getEnd(), other.getEnd() );
    }

    /**
     * Helper for toString.
     *
     * @return the String like [a,b)
     */
    default String rangeToString() {
        return "[" + getStart() + "," + getEnd() + ")";

    }

    /**
     * Helper to put endpoints or ranges in natural order. The minimum element
     * is put at position 0, the maximum at position 1.
     *
     * @param <Y> type of elements to sort
     * @param a arrays size 2 of input elements
     * @return sorted input array.
     */
    static <Y extends Comparable<? super Y>> Y[] minmax( Y... a ) {
        if ( a[ 0 ].compareTo( a[ 1 ] ) > 0 ) {
            Y y = a[ 0 ];
            a[ 0 ] = a[ 1 ];
            a[ 1 ] = y;
        }
        return a;
    }

    /**
     * Create a new Range using part of this range as the input. It is a helper
     * to make some other operations easier to implement.
     *
     * @param startInclusive start of the new range
     * @param endExclusive end of the new range
     * @return new range
     */
    R between( T startInclusive, T endExclusive );

    /**
     * Compare this range with the other range. Only the start points of the
     * ranges are considered.
     *
     * @param other to compare with this
     * @return integer &lt; 0, == 0, or &gt; 0 for this start less, equal, or
     * greater than other start.
     */
    @Override
    default int compareTo( Range<R, T, U> other ) {
        return this.getStart().compareTo( other.getStart() );
    }

    /**
     * Cut this range by a cutting range, producing a range that has an overlap
     * between this and the cutter.It does not modify this nor the other range,
     * but instead returns a new range.
     *
     * @param other that cuts this range
     * @return an Optional{@code Range<T,U>>} which is not empty when this and
     * cutter have an overlap.
     */
    default Optional<? extends R> commonRange( Range<R, T, U> other ) {
        //TODO A5B implement commonRange\
        T newStart;
        T newEnd;
        if(getStart().compareTo(other.getStart()) >= 0){
            newStart = other.getStart();
        }
        else {
            newStart = getStart();
        }

        if(getEnd().compareTo(other.getEnd()) >= 0){
            newEnd = getEnd();
        }
        else {
            newEnd = other.getEnd();
        }
//        Optional<? extends R> opt = ;

        return null;

    }
    /**
     * Test if other range is fully contained in this range, that is all points
     * of other are also part of this range.
     *
     * Note the handling of the end value. With same ends, contains is still
     * possible, in other words [ac) contains [bc) and [ac).
     *
     * @param other range
     * @return is the other range completely inside this range?
     */
    default boolean contains( Range<R, T, U> other ) {
        //TODO A4B implement contains(Range other)

        return other.getStart().compareTo(getStart()) > 0 && other.getEnd().compareTo(getEnd()) <= 0; // checking if other start is after this start and if other end is equal or smaller than this end
    }

    /**
     * Try to replace this range with another Range, as if it would punch
     * through this range and replaces the knocked out part by it selves.The
     * operation results in a stream that contains the remainders (if any) of
     * this range and the punch, if the punch would be exactly on target, that
     * is within the bounds of this range.For example when this is [a,c) and the
     * punch is [a,b), the result would be [[a-b),[b-c)]. The stream will
     * contain a minimum of 1 and maximum of 3 Ranges.
     *
     * @param punch to knockout parts of this range
     * @return a stream, either containing this range on a miss or the parts of
     * this range if there is something left over, and the punch between the
     * parts, or before or after the left over part.
     *
     *
     */
    default Stream<Range<R, T, U>> punchThrough( Range<R, T, U> punch ) {
        //TODO A6B Study punchThrough and improve coverage.
        if ( !this.contains( punch ) ) {
            // missed: this
            return Stream.of( this );
        }
        if ( this.rangeEquals( punch ) ) {
            // full punch: punch
            return Stream.of( punch );
        }
        if ( this.getStart().equals( punch.getStart() ) ) {
            // left punch: punch, remainder
            Range<R, T, U> remainder = between( punch.getEnd(), this.getEnd() );
            return Stream.of( punch, remainder );
        }
        if ( this.getEnd().equals( punch.getEnd() ) ) {
            // right punch: remainder punch
            Range<R, T, U> remainder = between( this.getStart(), punch.
                    getStart() );
            return Stream.of( remainder, punch );
        }
        Range<R, T, U> leftRemainder = between( this.getStart(), punch.
                getStart() );
        Range<R, T, U> rightRemainder = between( punch.getEnd(), this.getEnd() );
        return Stream.of( leftRemainder, punch, rightRemainder );
    }
}
