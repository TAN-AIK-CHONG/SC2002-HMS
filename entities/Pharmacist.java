package entities;
public class Pharmacist extends Staff {
    public Pharmacist(String id, String name, Gender gender, int age, String pw){
        super(id, pw, name, gender, age, "pharmacist");
    }

    public void fulfillPrescription(Appointment appointment, String medicationName) {
        for (Prescription prescription : appointment.getPrescriptions()) {
            if (prescription.getMedicationName().equals(medicationName)) {
                prescription.setStatus("dispensed");
            }
        }
    }
}
