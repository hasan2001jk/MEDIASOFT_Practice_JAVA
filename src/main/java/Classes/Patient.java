package Classes;

import java.util.Date;

public class Patient {
    private int patientId;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date registrationDate;

    public Patient() {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.registrationDate = registrationDate;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
