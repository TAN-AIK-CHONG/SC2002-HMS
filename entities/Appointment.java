package entities;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Appointment {
    private static int idCounter = 1;
    private int appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDate date;
    private LocalTime time;
    private String status; // "confirmed", "pending", "canceled", "completed", etc
    private String typeOfService; // consultation, blood test, etc
    private String notes;
    private List<Prescription> prescriptions;

    public Appointment(String patientId, String doctorId, LocalDate date, LocalTime time, String typeOfService) {
        this.appointmentId = idCounter++;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.status = "pending";
        this.typeOfService = typeOfService;
        this.notes = "";
        this.prescriptions = new ArrayList<>();
    }

    // Getters and Setters
    public int getAppointmentId() {
        return appointmentId;
    }
    public String getPatientId() {
        return patientId;
    }
    public String getDoctorId() {
        return doctorId;
    }
    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() {
        return time;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getTypeOfService() {
        return typeOfService;
    }
    public String getNotes() {
        return notes;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }
    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    // Methods for adding and managing prescriptions
    public void addPrescription(String medicationName) {
        prescriptions.add(new Prescription(medicationName));
    }

    public String toCSV() {
        StringBuilder csv = new StringBuilder(appointmentId + "," + patientId + "," + doctorId + "," + date + "," + time + "," + status + "," + typeOfService + "," + notes);
        for (Prescription prescription : prescriptions) {
            csv.append(",").append(prescription.toCSV());
        }
        return csv.toString();
    }

    public String toString() {
        StringBuilder result = new StringBuilder("Appointment ID: " + appointmentId + ", Date: " + date + ", Time: " + time + ", Status: " + status + ", Service: " + typeOfService + ", Notes: " + notes);
        for (Prescription prescription : prescriptions) {
            result.append("\n    ").append(prescription);
        }
        return result.toString();
    }
}
