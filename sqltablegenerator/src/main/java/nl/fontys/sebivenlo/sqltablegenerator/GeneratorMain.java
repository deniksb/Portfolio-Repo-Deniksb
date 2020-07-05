package nl.fontys.sebivenlo.sqltablegenerator;

import entities.CourseModule;
import entities.Student;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create sql tables form classes.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public class GeneratorMain {

    public static void main( String[] args ) {

//        if ( args.length < 1 ) {
//            System.out.println( "Supply class name" );
//            System.exit( 1 );
//        }

//        for ( String arg : args ) {
        String arg = Student.class.getName();
            Class<?> clz;
            try {
                clz = Class.forName( arg );
                PGTableCreator<?> creator = new PGTableCreator<>( clz );
                creator.createTable( System.out );
            } catch ( ClassNotFoundException | IOException ex ) {
                Logger.getLogger( GeneratorMain.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }


}
