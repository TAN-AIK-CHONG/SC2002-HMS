package entities.appointments;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AOR {
    private int apptID;
    private String patientID;
    private String doctorID;
    private LocalDate date;
    private LocalTime time;
    private ApptStatus status;
    private TypeOfService tos;
    private String consultationNotes;
    private List<ApptPrescription> prescriptions;

    public AOR(int apptID, String patientID, String doctorID, LocalDate date, LocalTime time, ApptStatus status,
            TypeOfService tos, String consultationNotes, List<ApptPrescription> prescriptions) {
        this.apptID = apptID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.time = time;
        this.status = status;
        this.tos = tos;
        this.consultationNotes = consultationNotes;
        this.prescriptions = prescriptions;
    }

    public int getApptID() {
        return apptID;
    }

    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
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

    public ApptStatus getStatus() {
        return status;
    }

    public void setStatus(ApptStatus status) {
        this.status = status;
    }

    public TypeOfService getTos() {
        return tos;
    }

    public void setTos(TypeOfService tos) {
        this.tos = tos;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public List<ApptPrescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<ApptPrescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    
    
}
