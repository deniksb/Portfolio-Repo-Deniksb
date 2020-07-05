package nl.fontys.sebivenlo.seasons;

import entities.Booking;
import java.io.Serializable;
import java.time.LocalDate;
import static java.time.LocalDate.of;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import static java.util.stream.Collectors.toCollection;
import java.util.stream.Stream;
import nl.fontys.sebivenlo.ranges.LocalDateRange;

/**
 * Set that guards against double bookings.
 *
 * <p>
 * Intended to be used as the bookings for one asset. The set contains bookings
 * and gaps, the later being of type LocalDateRange.</p>
 *
 * <p>
 * The bookings are kept in start date order. Gaps and bookings never overlap.
 * All gaps and bookings have a left and right neighbor that touches it, with
 * the exception of the first or last or only element in the set.</p>
 *
 * <p>
 * The invariant maintained by this booking set is that the gaps and bookings
 * put one after the other in natural order span the booking horizon or total
 * date range of this set. Whenever a booking is made a gap is split up or
 * replaced by the booking.</p>
 *
 * <p>
 * A booking not yet made can only be made if there is a gap that can fully
 * contain the booking. There can only ever be exactly one such gap.</p>
 *
 * <p>
 * If the booking touches the leftmost of rightmost boundary of the gap, it will
 * replace said part of the gap. If the booking touches both gap boundaries, it
 * will replace the gap completely. If neither boundary is touched, the booking
 * replaces a part of the gap somewhere between the boundaries of the gap.</p>
 *
 * <b>Graphical:</b><br>
 * <img src='doc-files/gaps-and-bookings.svg' alt='gaps and bookings'>
 *
 * <p>
 * The implementation uses features of a {@link java.util.SortedSet SortedSet},
 * in particular that the contains method is comparator based instead of equals
 * based.</p>
 * <br>
 * This class is Thread Safe.
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
class BookingSet implements Serializable {

    /**
     * TreeSet is a sorted set and we want to make use of the comparaising based
     * equals.
     */
    private TreeSet<LocalDateRange> set = new TreeSet<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private int size = 0;
    private final Lock writeLock = readWriteLock.writeLock();

    public BookingSet( int year ) {
        set.add( new LocalDateRange( of( year, 1, 1 ), of( year + 1, 1, 1 ) ) );
    }

    /**
     * Attempt to add a booking, replacing part or whole of a gap.
     *
     * <p>
     * NOTE: To get a stream of LocalDateRange from the punchThrough operation
     * defined in {@link nl.fontys.sebivenlo.ranges.Range Range}, use the
     * cast-trick explained in
     * <a href='https://prc2.fontysvenlo.org/2020/week13.html#_half_way_solution_for_stream_and_optional'>prc2
     * wwk 13</a>.
     * </p>
     * If the booking succeeds, true is returned, false otherwise.
     *
     * @param booking add a booking
     * @return an optional new booking set.
     */
    boolean addBooking( Booking booking ) {
        writeLock.lock();
        try {
            if ( !isAvailable( booking ) ) {
                return false;
            }
            // update set with set containing the booking. Use stream operations.
            //TODO B1B implement addBooking
            
            return true;
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Find a non-booking range that can contain the requested range. Because
     * the date ranges are stored in natural order, using any match will
     * suffice.
     *
     * @param r range to fit
     * @return true if fits
     */
    boolean isAvailable( LocalDateRange r ) {
        readLock.lock();
        try {
            //TODO B1B implement stream operation to find if booking is available
            return false;
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Delete a booking and mend the gap, by removing the neighbors and
     * replacing all by a range spanning the that resulting gap.
     *
     *
     * This cannot be done with stream operations, because the previous and next
     * element are not available inside a stream.
     *
     * If a booking is in the set, assume booking is range b-c, previous a-b and
     * next is c-d. A booking is in the set when the booking is rangeEquals to
     * the bookings in the set.
     *
     * Iterate over the ranges using an Iterator, remembering previous (ab) and
     * next (cd).
     *
     *
     * When found, delete booking, previous a-b if not null, and next cd if not
     * null, insert new Range(a-d).
     *
     *
     * @param booking to delete
     * @return a copy of this set where the booking is no longer present.
     */
    boolean deleteBooking( Booking booking ) {
        writeLock.lock();
        try {
            Iterator<LocalDateRange> itr = set.iterator();
            LocalDateRange previous = null;
            LocalDateRange next = null;
            LocalDateRange current = itr.next();
            //TODO B2B step 1 find booking and previous and next if present
            // end step 1
            boolean found = current.rangeEquals( booking );
            //TODO B2C step 2 combine previous (if not booking), current, 
            // and next (if not booking) into one range and _replace_ all with said range.
             // end step 2
            return found;
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Return the number of booking on this asset within the booking-horizon of
     * this booking set.
     *
     * @return the count
     */
    int size() {

        readLock.lock();
        try {

            return size;
        } finally {
            readLock.unlock();
        }
    }

    /**
     * This toString makes a shallow copy to iterate over to prevent keeping the
     * lock too long.
     *
     * @return string for analysis
     */
    @Override
    public String toString() {
        List<LocalDateRange> myset;
        readLock.lock();
        try {
            myset = new ArrayList<>( set );
        } finally {
            readLock.unlock();
        }

        StringBuilder sb = new StringBuilder();
        myset.forEach( x -> {
            sb.append( "\t[" ).append( x.getStart() )
                    .append( "," ).append( x.getEnd() )
                    .append( ")" ).append(
                    ( x instanceof Booking ) ? x.toString() : "" )
                    .append( "\n" );

        } );

        return "BookingSet{" + " set=\n" + sb.toString() + '}';
    }

    /**
     * Get the number of ranges in the set. The number of elements in the set is
     * at least one, even when there is no booking in this set. The number
     * returned is the number of bookings plus the number of gaps between them
     * or enclosing them. Possible arrangements of Bookings and gaps are gBg BgB
     * gBgBgBg, gBBBgBB etc, but never two gaps touching.
     *
     * @return the number
     */
    int setSize() {
        return set.size();

    }

    /**
     * Get the last element in the set. It can be an empty date range or an
     * actual booking. Due to the invariant maintained by this class, there
     * always is at least one element.
     *
     * @return first element.
     */
    LocalDateRange lastRange() {
        return set.last();
    }

    /**
     * Get the first element in the set. It can be an empty date range or an
     * actual booking. Due to the invariant maintained by this class, there
     * always is at least one element.
     *
     * @return last element
     */
    LocalDateRange firstRange() {
        return set.first();
    }

    /**
     * Get the set of actual bookings as a stream.
     *
     * @return the bookings as stream
     */
    Stream<Booking> stream() {
        return set.stream().filter( e -> e instanceof Booking ).map( 
                Booking.class::cast );
    }
}
