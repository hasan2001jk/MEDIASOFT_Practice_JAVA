package Classes;

import java.sql.Time;
import java.util.Date;


public class Recept {
    private Date day;
    private Time time;
    private  ReceptStatus receptStatus;
    private int doctorId;
    private int patientId;

    public Recept() {
        this.day = day;
        this.time = time;
        this.receptStatus = receptStatus;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public ReceptStatus getReceptStatus() {
        return receptStatus;
    }

    public void setReceptStatus(ReceptStatus receptStatus) {
        this.receptStatus = receptStatus;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
}
