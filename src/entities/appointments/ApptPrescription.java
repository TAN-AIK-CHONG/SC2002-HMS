package entities.appointments;

import entities.PrescriptionStatus;

public class ApptPrescription {
    private String medicationName;
    private PrescriptionStatus status;

    public ApptPrescription(String medicationName) {
        this.medicationName = medicationName;
        this.status = PrescriptionStatus.PENDING;
    }

    public ApptPrescription(String medicationName, PrescriptionStatus status) {
        this.medicationName = medicationName;
        this.status = status;
    }


    public String getMedicationName() {
        return medicationName;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(PrescriptionStatus status) {
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
