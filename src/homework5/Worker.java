package homework5;

import java.io.*;
import java.time.LocalDate;

class Worker implements Serializable {
    private String surname;
    private String position;
    private String gender;
    private LocalDate dateOfBirth;

    public Worker(String surname, String position, String gender, LocalDate dateOfBirth) {
        this.surname = surname;
        this.position = position;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public String getSurname() {
        return surname;
    }

    public String getPosition() {
        return position;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "Surname: " + surname + ", Position: " + position + ", Gender: " + gender + ", Date of Birth: " + dateOfBirth;
    }
}



