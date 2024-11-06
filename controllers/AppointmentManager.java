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
    public void viewByFilter(ApptStatus status){
        List<ApptSlot> slots = ApptSlotRepository.load();
        for (ApptSlot appointment : slots){
            if (appointment.getStatus() == status){
                appointment.view();
                System.out.println();
            }
        }
    }
    
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
        List<ApptSlot> slots = new ArrayList<>(ApptSlotRepository.load());
        for (int i = 0; i < slots.size(); i++) {
            ApptSlot appointment = slots.get(i);
            if (appointment.getApptID().equals(apptID) && appointment.getStatus() == ApptStatus.AVAILABLE) {
                appointment.setStatus(ApptStatus.PENDING);
                appointment.setPatientID(patientID);
                ApptSlotRepository.store(slots);
                return;
            }
        }
        System.out.println("No such appointment found");
    }

    public void reschedule(String prevApptID, String newApptID){
        String patientID=null;
        List<ApptSlot> slots = new ArrayList<>(ApptSlotRepository.load());
        for (int i = 0; i < slots.size(); i++) {
            ApptSlot appointment = slots.get(i);
            if (appointment.getApptID().equals(prevApptID) && 
                (appointment.getStatus() == ApptStatus.CONFIRMED || appointment.getStatus() == ApptStatus.PENDING)) {
                appointment.setStatus(ApptStatus.AVAILABLE);
                patientID = appointment.getPatientID();
                appointment.setPatientID(null);
            }
            else if(appointment.getApptID().equals(newApptID) && appointment.getStatus() == ApptStatus.AVAILABLE){
                appointment.setStatus(ApptStatus.PENDING);
                appointment.setPatientID(patientID);
            }
        }
        ApptSlotRepository.store(slots);
    }

    public void cancel(String apptID){
        List<ApptSlot> slots = new ArrayList<>(ApptSlotRepository.load());
        for (int i = 0; i < slots.size(); i++) {
            ApptSlot appointment = slots.get(i);
            if (appointment.getApptID().equals(apptID) && appointment.getStatus() == ApptStatus.CONFIRMED) {
                appointment.setStatus(ApptStatus.CANCELLED);
                ApptSlotRepository.store(slots);
                return;
            }
        }
        System.out.println("No such appointment found");
    }

    public void viewAOR(String apptID){
        //view AOR for that appt ID
    }
    
}
