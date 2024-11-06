package controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import entities.appointments.ApptSlot;
import entities.appointments.ApptStatus;
import filehandlers.ApptSlotRepository;

public class AppointmentManager {
    // FOR BOTH
    public void viewByFilterDoc(String doctorID, ApptStatus status){
        List<ApptSlot> slots = ApptSlotRepository.load();
        for (ApptSlot appointment : slots){
            if (doctorID.equals(appointment.getDoctorID()) && appointment.getStatus() == status){
                appointment.view();
                System.out.println();
            }
        }
    }

    public void viewByFilterPatient(String patientID, ApptStatus status){
        List<ApptSlot> slots = ApptSlotRepository.load();
        for (ApptSlot appointment : slots){
            if (patientID.equals(appointment.getPatientID()) && appointment.getStatus() == status){
                appointment.view();
                System.out.println();
            }
        }
    }

    // FOR DOCTORS
    public void setAvailable(String doctorID, LocalDate date, LocalTime time){
        List<ApptSlot> slots = new ArrayList<>(ApptSlotRepository.load());
        ApptSlot newAppt = new ApptSlot(date, time, doctorID);
        slots.add(newAppt);
        ApptSlotRepository.store(slots);
    }

    public void handlePending(String apptID, ApptStatus status){
        List<ApptSlot> slots = new ArrayList<>(ApptSlotRepository.load());
        for (int i = 0; i < slots.size(); i++) {
            ApptSlot appointment = slots.get(i);
            if (appointment.getApptID().equals(apptID) && appointment.getStatus().equals(ApptStatus.PENDING)) {
                appointment.setStatus(status);
                ApptSlotRepository.store(slots);
                return;
            }
        }
        System.out.println("No such appointment found");
    }

    public void makeAOR(String apptID){
        //make AOR for that appt ID
        //store aor in csv
    }

    // FOR PATIENTS
    public void schedule(String apptID, String patientID){
        //set status pending for that appt ID
        //set patient ID for that appt ID
    }

    public void reschedule(String prevApptID, String newApptID){
        //set status available for prev appt ID
        //set patient ID = null for prev appt ID
        //set status pending for new appt ID
    }

    public void cancel(String apptID){
        //set status to cancelled for that appt ID
        //store in csv
    }

    public void viewAOR(String apptID){
        //view AOR for that appt ID
    }
    
}
