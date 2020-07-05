package nl.fontys.sebivenlo.csvobjectstream;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Reads CSV files and turns the records into objects. Can read source from
 * local files but also from urls.
 *
 * Version 1.1 adds both the separator char (defaulted to semi colon) and line
 * filter, to allow commented csv files.
 *
 * @author Pieter van den Hombergh (p dot vandenhombergh at fontys dot nl)
 * @param <T> type of the created objects.
 */
public class CSVObjectStream<T> {

    final Path filePath;
    final String splitString;
    final Predicate<String> lineFilter;

    /**
     * Construct the stream from a path.
     *
     * @param filePath the source of the csv records
     */
    public CSVObjectStream( Path filePath ) {
        this( filePath, ";" );
    }

    /**
     * Construct the stream from a path with given separator.
     *
     * @param filePath the source of the csv records
     * @param splitString separator in csv file, like ";" or "," to split
     * strings with {@code String.split(String)}
     */
    public CSVObjectStream( Path filePath, String splitString ) {
        this.filePath = filePath;
        this.splitString = splitString;
        this.lineFilter = x -> true;

    }

    /**
     * Construct the stream from a path with given separator and lineFilter.
     *
     * @param filePath specifies source
     * @param splitString specifies csv separator
     * @param lineFilter set filter, to filter out (un) wanted
     */
    public CSVObjectStream( Path filePath, String splitString, Predicate<String> lineFilter ) {
        this.filePath = filePath;
        this.splitString = splitString;
        this.lineFilter = lineFilter;
    }

    /**
     * The actual stream. The creator makes the objects of type T, the rowfilter
     * makes sure that a row is suitable for the creator.
     *
     * @param creator from {@code String[] -> T}
     * @param rowFilter to make it possible to reject rows, e.g. the header of
     * the csv file.
     * @return the stream of T
     * @throws IOException when the file cannot be processed.
     */
    public Stream<T> stream( Function<? super String[], ? extends T> creator,
            Predicate<? super String[]> rowFilter ) throws
            IOException {
        return Files.lines( filePath )
                .filter( this.lineFilter )
                .map( s -> s.split( this.splitString ) )
                .filter( rowFilter )
                .map( creator );
    }

    /**
     * Collect stream to a list.
     *
     * @param creator from {@code String[] -> T}
     * @param rowFilter to make it possible to reject rows, e.g. the header of
     * the csv file.
     * @return the created objects in a list in file encounter order.
     * @throws IOException when csv file cannot be read.
     */
    public List<T> asList( Function<? super String[], ? extends T> creator,
            Predicate<? super String[]> rowFilter ) throws
            IOException {

        return this.stream( creator, rowFilter ).collect( Collectors.toList() );
    }

}
