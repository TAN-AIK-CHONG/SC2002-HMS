package entities.appointments;

import java.time.LocalDate;
import java.time.LocalTime;

import utility.IDGenerator;

public class ApptSlot {
    private String apptID;
    private LocalDate date;
    private LocalTime time;
    private String doctorID;
    private String patientID;
    private ApptStatus status;

    //default constructor
    public ApptSlot(LocalDate date, LocalTime time, String doctorID) {
        this.apptID = IDGenerator.generateID(5);
        this.date = date;
        this.time = time;
        this.doctorID = doctorID;
        this.patientID = null;
        this.status = ApptStatus.AVAILABLE;
    }

    //loaded
    public ApptSlot(String apptID, LocalDate date, LocalTime time, String doctorID, String patientID, ApptStatus status) {
        this.apptID = apptID;
        this.date = date;
        this.time = time;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.status = status;
    }

    public void view(){
        System.out.print("Appointment ID: " + this.apptID);
        System.out.print(", Date: " + this.date);
        System.out.print(", Time: " + this.time);
        System.out.print(", Doctor ID: " + this.doctorID);
        System.out.print(", Patient ID: " + this.patientID);
        System.out.print(", Status: " + this.status.toString());
    }

    //setters and getters
    public String getApptID() {
        return apptID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public ApptStatus getStatus() {
        return status;
    }

    public void setStatus(ApptStatus status) {
        this.status = status;
    }
}
