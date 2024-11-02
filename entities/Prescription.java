package entities;

public class Prescription {
    private String medicationName;
    private String status; // e.g., "pending", "dispensed"

    public Prescription(String medicationName) {
        this.medicationName = medicationName;
        this.status = "pending"; // Default status
    }

    // getters and setters
    public String getMedicationName() { return medicationName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String toCSV() {
        return medicationName + ":" + status;
    }

    public String toString() {
        return "Prescription: " + medicationName + ", Status: " + status;
    }
}
