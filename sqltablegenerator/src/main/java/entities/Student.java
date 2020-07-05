package entities;

import annotations.ID;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Simple student with LocalDate birthday.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */

@Data
@AllArgsConstructor
public class Student {

    @ID
    private Integer snummer;
    private String lastname;
    private String tussenvoegsel;
    private String firstname;
    private LocalDate dob;
    private short cohort;
    private String email;
    private String gender;
    private String student_class;
}
