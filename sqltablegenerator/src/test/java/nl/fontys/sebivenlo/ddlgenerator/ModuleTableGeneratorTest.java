/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.sebivenlo.ddlgenerator;

import entities.CourseModule;
import static org.assertj.core.api.Assertions.fail;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class ModuleTableGeneratorTest extends GeneratorTestBase {


    
    
    /**
     * Create a table once, then run all tests on it.
     */
    public ModuleTableGeneratorTest() {
        super( CourseModule.class );
    }

    /**
     * Assert all column definitions are correct.Each test data line contains
     * field name, type and modifiers like NOT NULL etc.
     *
     * @param fieldName sic
     * @param expectedValue sic
     */
    @ParameterizedTest
    @CsvSource( {
        "moduleid,SERIAL",
        "moduleid,PRIMARY KEY",
        "name,NOT NULL",
        "credits,NOT NULL",
        "credits,DEFAULT (5)",
        "credits,CHECK",
        "credits,(credits > 0)"
    } )
    public void columnDefs( String fieldName, String expectedValue ) {
        //TODO assert field and attribute using assertTypeConversion
//        fail( "method columnDefs reached end. You know what to do." );
    }

}
