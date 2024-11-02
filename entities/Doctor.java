package entities;

import java.util.List;

public class Doctor extends Staff {
    public Doctor(String id, String name, Gender gender, int age, String pw) {
        super(id, pw, name, gender, age, "doctor");
    }

    public void addAppointmentOutcome(Appointment appointment, String notes, List<String> medications) {
        appointment.setNotes(notes);
        for (String medication : medications) {
            appointment.addPrescription(medication);
        }
        appointment.setStatus("completed");
    }
}
