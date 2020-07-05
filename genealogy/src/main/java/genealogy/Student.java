package genealogy;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class Student implements Cloneable, Serializable {

    private final long studentNummer;
    private final String name;
    private final LocalDate dob;

    public Student(long studentNummer, String name, LocalDate dob) {
        this.studentNummer = studentNummer;
        this.name = name;
        this.dob = dob;
    }

    public long getStudentNummer() {
        return studentNummer;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.studentNummer ^ (this.studentNummer >>> 32));
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.dob);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.studentNummer != other.studentNummer) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.dob, other.dob)) {
            return false;
        }
        return true;
    }

}
