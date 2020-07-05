package entities;

import annotations.Check;
import lombok.AllArgsConstructor;
import lombok.Data;
import annotations.Default;
import annotations.ID;
import annotations.NotNull;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
@Data
@AllArgsConstructor
public class CourseModule {

    @ID
    private Long moduleid;
    @NotNull
    String name;
    @NotNull
    @Default( value = "5" )
    @Check( value = "credits > 0" )
    Integer credits;

//    public CourseModule( Long moduleid, String name ) {
//        this( moduleid, name, 5 );
//    }
}
