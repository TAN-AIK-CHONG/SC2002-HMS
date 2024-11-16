package entities.appointments;

import entities.Prescription;

public class ApptPrescription {
    private String medicationName;
    private Prescription status;

    public ApptPrescription(String medicationName) {
        this.medicationName = medicationName;
        this.status = Prescription.PENDING;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(Prescription status) {
        this.status = status;
    }

    public String toCSV() {
        return medicationName + ":" + status.toString();
    }

    @Override
    public String toString() {
        return "Prescription: " + medicationName + ", Status: " + status.toString();
    }
}
