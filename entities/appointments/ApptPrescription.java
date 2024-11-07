package entities.appointments;

public class ApptPrescription {
    enum Status {PENDING, DISPENSED};

    private String medicationName;
    private Status status;

    public ApptPrescription(String medicationName) {
        this.medicationName = medicationName;
        this.status = Status.PENDING; // Default status
    }

    // getters and setters
    public String getMedicationName() { return medicationName; }
    public String getStatus() { return status.toString(); }
    public void setStatus(Status status) { this.status = status; }

    public String toCSV() {
        return medicationName + ":" + status.toString();
    }

    public String toString() {
        return "Prescription: " + medicationName + ", Status: " + status.toString();
    }
}
