package nl.fontys.sebivenlo.sqltablegenerator;

import annotations.Default;
import annotations.NotNull;
import annotations.Check;
import annotations.ID;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.joining;

/**
 * Create table defintion for a class.
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 * @param <E> type type of the entity
 */
public class PGTableCreator<E> implements TableCreator<E> {

    final Class<E> entityType;

    public PGTableCreator( Class<E> entityType ) {
        this.entityType = entityType;
    }

    @Override
    public void createTable( Appendable out ) throws IOException {
        
            out.append( "CREATE TABLE " + entityType.getSimpleName().toLowerCase() + "s (\n" );
            Field[] declaredFields = entityType.getDeclaredFields();
            List<String> outLines = new ArrayList<>();
            for ( Field field : declaredFields ) {
                StringBuilder ol = new StringBuilder();
                String pgType = TypeMappings.getPGTypeName( field.getType() );
                String declaration = processAnnotations( pgType, field );
                ol.append( "    " )
                        .append( declaration );
                outLines.add( ol.toString() );

            }
            String body = outLines.stream().collect( joining( "," + System.lineSeparator() ) );
            out.append( body )
                    .append( System.lineSeparator() )
                    .append( ");" )
                    .append( System.lineSeparator() )
                    .append( System.lineSeparator() );


        

    }

    
    /**
     * Process the name and annotations on the field to a PostgreSQL column definition.
     * 
     * <ul>
     * <li>{@code @ID} should result in 'PRIMARY KEY'</li>
     * <li>{@code @NotNull} should result in 'NOT NULL'</li>
     * <li>{@code @Default (value)} should result in 'DEFAULT (value)'</li>
     * <li>{@code @Check (value)} should result in 'CHECK (value)'</li>
     * </ul>
     * @param pgTypeName name of the field/ column
     * @param field the java field definition, which may have annotations to be processed.
     * @return the column definition
     */
    String processAnnotations( String pgTypeName, Field field ) {
        //TODO implement method processAnnotations
//        System.out.println(Arrays.toString(field.getAnnotations()));
        StringBuilder annotationToAppend = new StringBuilder();
        if(!(field.getAnnotations().length == 0)) {
            for (int i = 0; i < field.getAnnotations().length; i++) {
                Annotation annotation = field.getAnnotations()[i];

                if (annotation.toString().contains("@annotations.ID")) {
                    annotationToAppend.append("PRIMARY KEY");

                }
                if (annotation.toString().contains("@annotations.NotNull()")) {
                    annotationToAppend.append("NOT NULL");
                }
                if (annotation.toString().contains("@annotations.Default")) {
                    annotationToAppend.append(" DEFAULT");
                    Default defaul = field.getAnnotation(Default.class);
                    annotationToAppend.append(" (" + defaul.value() + ")");

                }
                if (annotation.toString().contains("@annotations.Check")) {
                    annotationToAppend.append(" CHECK");
                    Check check = field.getAnnotation(Check.class);
                    annotationToAppend.append(" (" + check.value() +")");
                }
//                System.out.println(Arrays.toString(field.getAnnotations()));

            }
        }


      return field.getName() + " " + pgTypeName + " " + annotationToAppend.toString();

    }
}
