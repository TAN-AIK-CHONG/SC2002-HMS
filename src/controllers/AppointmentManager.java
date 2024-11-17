package controllers;

import entities.PrescriptionStatus;
import entities.appointments.*;
import filehandlers.AORRepository;
import filehandlers.ApptSlotRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {
    // FOR BOTH
    public void viewByFilter(ApptStatus status) {
        List<ApptSlot> slots = ApptSlotRepository.load();
        for (ApptSlot appointment : slots) {
            if (appointment.getStatus() == status) {
                appointment.view();
                System.out.println();
            }
        }
    }

    public void viewByFilterDoc(String doctorID, ApptStatus status) {
        List<ApptSlot> slots = ApptSlotRepository.load();
        for (ApptSlot appointment : slots) {
            if (doctorID.equals(appointment.getDoctorID()) && appointment.getStatus() == status) {
                appointment.view();
                System.out.println();
            }
        }
    }

    public void viewByFilterPatient(String patientID, ApptStatus status) {
        List<ApptSlot> slots = ApptSlotRepository.load();
        for (ApptSlot appointment : slots) {
            if (patientID.equals(appointment.getPatientID()) && appointment.getStatus() == status) {
                appointment.view();
                System.out.println();
            }
        }
    }

    // FOR DOCTORS
    public void setAvailable(String doctorID, LocalDate date, LocalTime time) {
        List<ApptSlot> slots = new ArrayList<>(ApptSlotRepository.load());
        ApptSlot newAppt = new ApptSlot(date, time, doctorID);
        slots.add(newAppt);
        ApptSlotRepository.store(slots);
    }

    public void handlePending(String apptID, ApptStatus status) {
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

    public void makeAOR(String apptID, String notes, TypeOfService tos, List<ApptPrescription> prescription, double apptBill) {
        // make AOR for that appt ID
        // store aor in csv
        List<ApptSlot> slots = ApptSlotRepository.load();

        for (int i = 0; i < slots.size(); i++) {
            ApptSlot appointment = slots.get(i);
            if (appointment.getApptID().equals(apptID) && (appointment.getStatus() == ApptStatus.CONFIRMED)) {
                appointment.setStatus(ApptStatus.COMPLETED);
                AOR aor = new AOR(appointment.getApptID(), appointment.getPatientID(), appointment.getDoctorID(),
                        appointment.getDate(), appointment.getTime(), appointment.getStatus(),
                        tos, notes, prescription, apptBill);

                List<AOR> aorList = new ArrayList<>(AORRepository.load());
                aorList.add(aor);
                AORRepository.store(aorList);
                ApptSlotRepository.store(slots);
                System.out.println("Appointment Outcome Record created and stored successfully.");
                return;
            }
        }
        System.out.println("No such confirmed or completed appointment.");
    }

    // FOR PATIENTS
    public void schedule(String apptID, String patientID) {
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

    public void reschedule(String patientID, String prevApptID, String newApptID) {
        List<ApptSlot> slots = new ArrayList<>(ApptSlotRepository.load());
        for (int i = 0; i < slots.size(); i++) {
            ApptSlot appointment = slots.get(i);
            if (appointment.getApptID().equals(prevApptID) && (appointment.getStatus() == ApptStatus.PENDING)) {
                appointment.setStatus(ApptStatus.AVAILABLE);
                patientID = appointment.getPatientID();
                appointment.setPatientID(null);
            } else if (appointment.getApptID().equals(newApptID) && appointment.getStatus() == ApptStatus.AVAILABLE) {
                appointment.setStatus(ApptStatus.PENDING);
                appointment.setPatientID(patientID);
            }
        }
        ApptSlotRepository.store(slots);
    }

    public void cancel(String apptID) {
        List<ApptSlot> slots = new ArrayList<>(ApptSlotRepository.load());
        for (int i = 0; i < slots.size(); i++) {
            ApptSlot appointment = slots.get(i);
            if (appointment.getApptID().equals(apptID) && appointment.getStatus() == ApptStatus.CONFIRMED ||
                    appointment.getStatus() == ApptStatus.PENDING) {
                appointment.setStatus(ApptStatus.AVAILABLE);
                appointment.setPatientID(null);
                ApptSlotRepository.store(slots);
                return;
            }
        }
        System.out.println("No such appointment found");
    }

    public void viewAOR(String apptID) {
        // view AOR for that appt ID
        List<AOR> aors = AORRepository.load();

        for (AOR aor : aors) {
            if (String.valueOf(aor.getApptID()).equals(apptID)) {
                System.out.println("Appointment Outcome Record for Appointment ID: " + apptID);
                System.out.println("Patient ID: " + aor.getPatientID());
                System.out.println("Doctor ID: " + aor.getDoctorID());
                System.out.println("Date: " + aor.getDate());
                System.out.println("Time: " + aor.getTime());
                System.out.println("Status: " + aor.getStatus());
                System.out.println("Type of Service: " + aor.getTos());
                System.out.println("Consultation Notes: " + aor.getConsultationNotes());
                System.out.println("Prescriptions: " + aor.getPrescriptions());
                System.out.println("Appointment Bill: " + aor.getAorcost());
                return;
            }
        }
        System.out.println("No Appointment Outcome Record found for Appointment ID " + apptID + ":");
    }

    public void updatePStatus(String apptID) {
        List<AOR> aors = AORRepository.load();
        for (AOR aor : aors) {
            if (aor.getApptID().equals(apptID)) {
                List<ApptPrescription> prescriptions = aor.getPrescriptions();
                if (!prescriptions.isEmpty()) {
                    try {
                        for (ApptPrescription prescription : prescriptions) {
                            prescription.setStatus(PrescriptionStatus.DISPENSED);
                        }
                        AORRepository.store(aors);
                        System.out.println("Prescription status updated successfully.");
                        return;
                    } catch (IllegalArgumentException e) {
                        System.out.println(
                                "Invalid status entered. Please enter a valid status: 'PENDING' or 'DISPENSED'.");
                        return;
                    }
                } else {
                    System.out.println("No prescriptions found for this appointment.");
                }
                return;
            }
        }
        System.out.println("Appointment not found with ID: " + apptID);
    }

    public String getPatientIDfromApptID(String apptID){
        List<AOR> aors = AORRepository.load();
        String returnID=null;
        for (AOR aor : aors) {
            if (aor.getApptID().equals(apptID)) {
                 returnID = aor.getPatientID();
            }
        }
        return returnID;
    }

    public List<String> getPrescriptionsfromApptID(String apptID){
        List<AOR> aors = AORRepository.load();
        List<String> medicineNames = new ArrayList<>();
        for (AOR aor : aors) {
            if (aor.getApptID().equals(apptID)) {
                List<ApptPrescription> prescriptions = aor.getPrescriptions();
                for (ApptPrescription prescription : prescriptions){
                    medicineNames.add(prescription.getMedicationName());
                }
            }
        }
        return medicineNames;
    }
}
