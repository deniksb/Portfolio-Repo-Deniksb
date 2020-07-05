package entities;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class Student implements Serializable {

    final int snummer;          //  0
    final String lastName;      //  1
    final String firstName;     //  2
    final String tussenvoegsel; //  3
    final String email;         //  4
    final int cohort;           //  5
    final String sClass;        //  6 
    final String gender;        //  7
    final LocalDate dob;        //  8

    public Student( 
            int snummer, 
            String lastName,
            String firstName, 
            String tussenvoegsel, 
            String email, 
            int cohort ,
            String sClass, 
            String gender,
            LocalDate dob ) {
        this.snummer = snummer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tussenvoegsel = tussenvoegsel;
        this.email = email;
        this.cohort=cohort;
        this.sClass = sClass;
        this.gender = gender;
        this.dob = dob;
    }

    public static Student fac( String[] params ) {
        return new Student( 
                Integer.parseInt( params[ 0 ] ),
                params[ 1 ],
                params[ 2 ],
                params[ 3 ],
                params[ 4 ],
                Integer.parseInt( params[ 5 ] ),
                params[ 6 ],
                params[ 7 ],
                LocalDate.parse( params[ 8 ] )
        );
    }

    public int getSnummer() {
        return snummer;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getEmail() {
        return email;
    }

    public String getsClass() {
        return sClass;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return "snummer=" + snummer + ", firstName=" + firstName +
                ", lastName=" + lastName + ", tussenvoegsel=" + tussenvoegsel +
                ", email=" + email + ", sClass=" + sClass + ", gender=" + gender +
                ", dob=" + dob;
    }
    
    
}
