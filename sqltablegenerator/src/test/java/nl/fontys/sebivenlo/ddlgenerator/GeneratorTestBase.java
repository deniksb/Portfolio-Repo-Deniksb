/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.sebivenlo.ddlgenerator;

import entities.Student;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.fontys.sebivenlo.sqltablegenerator.PGTableCreator;
import nl.fontys.sebivenlo.sqltablegenerator.TableCreator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
abstract class GeneratorTestBase {

    final List<String> tableDefinition;
    final Class<?> clz;
    final String tableName;
    public GeneratorTestBase( Class<?> clz ) {
        this.clz=clz;
        this.tableName= clz.getSimpleName().toLowerCase()+'s';
        this.tableDefinition = createTable( clz );
    }
    
    
    public static List<String> createTable( Class<?> clz ) {
        TableCreator<?> generator = new PGTableCreator<>( clz );
        StringBuilder sb = new StringBuilder();
        try {
            generator.createTable( sb );
        } catch ( Exception ex ) {
            Logger.getLogger( GeneratorTestBase.class.getName() ).log( Level.SEVERE, null, ex );
        }
        String table = sb.toString();
//        System.out.println( "table = " + table );
        return Arrays.asList( table.split( ",?\r?\n" ) );
    }

    /**
     * Helper test method that find the line with the fieldName and inspects
     * that it has the proper type declaration.
     *
     * The method checks that the static lines field of this test class contains
     * the field name, and that the line containing that field name contains the
     * expected type string.
     *
     * Use the outcome of the stream expression.
     *
     * @param tableDef the sql table definition as a list of strings
     * @param fieldName java field and database column name
     * @param expectedDefinition type declaration
     */
    void assertTypeConversion( List<String> tableDef, String fieldName, String expectedDefinition ) {
        Optional<String> columnDefinition = tableDef.stream()
//                .peek( System.out::println )
                .filter( s -> s.contains( fieldName ) ).findAny();
        //TODO assert that column name is present and contains expected type
        
    }

    /**
     * Assert that the table name is included.
     */
    @Test
    public void tableName() {
        //TODO make sure table name is 'students'
//        fail("test tableName not implemented");
    }

    //    @Disabled( "Think TDD" )
    @Test
    void startWithCREATE() {
        //TODO assert that the tableDefinition start with 'CREATE TABLE
//        fail( "method startWithCREATE reached end. You know what to do." );
    }
    
}
