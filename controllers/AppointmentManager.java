package controllers;

import entities.Appointment;
import entities.Prescription;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {
    private List<Appointment> appointments = new ArrayList<>();
    private static final String FILE_PATH = "database/AppointmentDatabase.csv";

    // load appt from CSV file
    public void loadAppointments() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Appointment appointment = new Appointment(data[1], data[2], LocalDate.parse(data[3]), LocalTime.parse(data[4]), data[6]);
                appointment.setStatus(data[5]);
                appointment.setNotes(data[7]);

                // Load prescriptions
                for (int i = 8; i < data.length; i++) {
                    String[] prescriptionData = data[i].split(":");
                    Prescription prescription = new Prescription(prescriptionData[0]);
                    prescription.setStatus(prescriptionData[1]);
                    appointment.getPrescriptions().add(prescription);
                }
                appointments.add(appointment);
            }
        } catch (IOException e) {
            System.out.println("Could not load appointments: " + e.getMessage());
        }
    }

    // saving appt to CSV file
    public void saveAppointments() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Appointment appointment : appointments) {
                writer.println(appointment.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Could not save appointments: " + e.getMessage());
        }
    }

    // scheduling an appt
    public Appointment scheduleAppointment(String patientId, String doctorId, LocalDate date, LocalTime time, String typeOfService) {
        Appointment newAppointment = new Appointment(patientId, doctorId, date, time, typeOfService);
        appointments.add(newAppointment);
        saveAppointments();
        return newAppointment;
    }

    // rescheduling an appt
    public boolean rescheduleAppointment(int appointmentId, LocalDate newDate, LocalTime newTime) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == appointmentId && appointment.getStatus().equals("confirmed")) {
                appointment.setDate(newDate);
                appointment.setTime(newTime);
                appointment.setStatus("pending"); // reset to pending if rescheduled
                saveAppointments();
                return true;
            }
        }
        return false;
    }

    // cancelling an appt
    public boolean cancelAppointment(int appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == appointmentId) {
                appointment.setStatus("canceled");
                saveAppointments();
                return true;
            }
        }
        return false;
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }
    // get available appointments for scheduling
    public List<Appointment> getAvailableAppointments() {
        List<Appointment> availableAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getStatus().equalsIgnoreCase("available")) {
                availableAppointments.add(appointment);
            }
        }
        return availableAppointments;
    }

    // get scheduled appointments for a specific patient
    public List<Appointment> getScheduledAppointmentsForPatient(String patientId) {
        List<Appointment> scheduledAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) &&
                    (appointment.getStatus().equalsIgnoreCase("pending") || appointment.getStatus().equalsIgnoreCase("confirmed"))) {
                scheduledAppointments.add(appointment);
            }
        }
        return scheduledAppointments;
    }

    // get completed appointments for a specific patient
    public List<Appointment> getCompletedAppointmentsForPatient(String patientId) {
        List<Appointment> completedAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId) && appointment.getStatus().equalsIgnoreCase("completed")) {
                completedAppointments.add(appointment);
            }
        }
        return completedAppointments;
    }

    // get upcoming appointments for a specific doctor
    public List<Appointment> getUpcomingAppointmentsForDoctor(String doctorId) {
        List<Appointment> upcomingAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) &&
                    (appointment.getStatus().equalsIgnoreCase("confirmed") || appointment.getStatus().equalsIgnoreCase("pending"))) {
                upcomingAppointments.add(appointment);
            }
        }
        return upcomingAppointments;
    }

    // get pending appointment requests for a specific doctor
    public List<Appointment> getPendingAppointmentsForDoctor(String doctorId) {
        List<Appointment> pendingRequests = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus().equalsIgnoreCase("pending")) {
                pendingRequests.add(appointment);
            }
        }
        return pendingRequests;
    }

    // update the status of an appointment
    public void updateAppointmentStatus(int appointmentId, String status) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == appointmentId) {
                appointment.setStatus(status);
                saveAppointments();
                break;
            }
        }
    }

    // record the outcome of an appointment
    public void recordAppointmentOutcome(int appointmentId, String serviceType, String notes, List<String> medications) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == appointmentId) {
                appointment.setStatus("completed");
                appointment.setNotes(notes);
                appointment.setTypeOfService(serviceType);
                for (String med : medications) {
                    appointment.addPrescription(med.trim());
                }
                saveAppointments();
                break;
            }
        }
    }
    // get completed appointments with prescriptions for the pharmacist
    public List<Appointment> getCompletedAppointmentsWithPrescriptions() {
        List<Appointment> completedAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getStatus().equalsIgnoreCase("completed") && !appointment.getPrescriptions().isEmpty()) {
                completedAppointments.add(appointment);
            }
        }
        return completedAppointments;
    }

    // update prescription status in a specific appointment
    public boolean updatePrescriptionStatus(int appointmentId, String medicationName, String newStatus) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == appointmentId) {
                for (Prescription prescription : appointment.getPrescriptions()) {
                    if (prescription.getMedicationName().equalsIgnoreCase(medicationName)) {
                        prescription.setStatus(newStatus);
                        saveAppointments();
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
