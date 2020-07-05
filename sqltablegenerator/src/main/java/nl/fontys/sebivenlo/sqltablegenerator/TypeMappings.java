package nl.fontys.sebivenlo.sqltablegenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Type mappings. Only implemented for postgresql.
 * 
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public enum TypeMappings {
    ;
    private static final Map<Class<?>, String> pgTypeMap = new HashMap<>();

    // not exhaustive
    static {
        pgTypeMap.put( java.lang.String.class, "TEXT" );
        pgTypeMap.put( java.lang.Character.class, "CHAR(1)" );
        pgTypeMap.put( java.lang.Integer.class, "INTEGER" );
        pgTypeMap.put( int.class, "INTEGER" );
        pgTypeMap.put( java.lang.Short.class, "SMALLINT" );
        pgTypeMap.put( short.class, "SMALLINT" );
        pgTypeMap.put( java.lang.Long.class, "BIGINT" );
        pgTypeMap.put( long.class, "BIGINT" );
        pgTypeMap.put( java.math.BigDecimal.class, "DECIMAL" );
        pgTypeMap.put( java.math.BigInteger.class, "NUMERIC" );
        pgTypeMap.put( java.lang.Float.class, "REAL" );
        pgTypeMap.put( float.class, "REAL" );
        pgTypeMap.put( java.lang.Double.class, "DOUBLE PRECISION" );
        pgTypeMap.put( double.class, "DOUBLE PRECISION" );
        pgTypeMap.put( java.time.LocalDate.class, "DATE" );
        pgTypeMap.put( java.time.LocalDateTime.class, "TIMESTAMP" );
    }

    static String getPGTypeName( Class<?> c ) {
        return pgTypeMap.get( c );
    }

}
