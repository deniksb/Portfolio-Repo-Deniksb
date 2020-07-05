package nl.fontys.sebivenlo.csvobjectstream;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import nl.fontys.sebivenlo.csvobjectstream.Factories.Student;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Test class for cvs object stream util class. This test class contains one
 * test only. See {@link CSVObjectStreamTest#test_with_students()}
 *
 * @author Pieter van den Hombergh (p dot vandenhombergh at fontys dot nl)
 */
public class CSVObjectStreamTest {

    /**
     * Test the CSVObjectStream class.
     * <ol>
     * <li>For the constructor call use {@code Factories::createStudent} as
     * creator. From the String "students.csv", create a Path
     * {@link java.nio.file.Path} using the Paths utility class in the same
     * package.
     * </li>
     * <li>Start the stream with {@code Student.class} as objectType parameter
     * and Use the regular expression (String) {@code "^\\d+$"} to create a
     * lambda expression as the predicate to ensure that the first string in the
     * array of strings contains only digits.
     * </li>
     * <li> Collect the resulting stream in a list and assert
     * <ul>
     * <li>The list is not null.</li>
     * <li>The list is not empty.</li>
     * <li>The list has 50 elements.</li>
     * <li>The list contains a student with the name "Olympia Oliphant" at index
     * three.</li>
     * </ul>
     * In all the asserts, use a message and make sure you put the arguments to
     * the assertXXX method in the proper order.
     * </li>
     * </ol>
     *
     * @throws URISyntaxException error on path
     * @throws IOException error with file
     */
    @Test
    public void test_with_students() throws URISyntaxException,
            IOException {
        Path path = Paths.get( "students.csv" ); // 1
        CSVObjectStream<Student> os
                = new CSVObjectStream<>( path, ";", s -> !s.startsWith( "#" ) ); // 1

        List<Student> list = os // 1
                .stream( Factories::createStudent, r -> r[ 0 ].matches( "\\d+" ) ) //3
                .collect( Collectors.toList() ); //2

        System.out.println( "l = " + list ); //0

        assertThat( list )
                .isNotEmpty()
                .hasSize( 50 );
        assertThat( list.get( 3 ) ).extracting( "name" ).isEqualTo( "Olympia Oliphant" );
    }

    //</editor-fold>
    /**
     * Test the ass list method. List is same as in previous method and contains
     * 50 elements, third element is Olympia.
     *
     * @throws URISyntaxException unexpected in test
     * @throws MalformedURLException unexpected in test
     * @throws IOException unexpected in test
     */
    @Test
    public void testAsList() throws URISyntaxException, MalformedURLException,
            IOException {
        Path path = Paths.get( "students.csv" ); // 1
        CSVObjectStream<Student> os
                = new CSVObjectStream<>( path ); // 1

        List<Student> l = os.asList( Factories::createStudent, r -> r[ 0 ].
                matches( "\\d+" ) );
        assertThat( l ).as( "list has elements missing" ).hasSize( 50 );
        assertThat( l.get( 3 ).name ).isEqualTo( "Olympia Oliphant" );
    }

}
