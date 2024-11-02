package userinterfaces;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import controllers.PatientManager;
import dbinterfaces.PatientRepository;
import entities.Patient;
import entities.Appointment;
import controllers.AppointmentManager;

public class PatientMenu implements IMenu {
    private Patient patient;
    private AppointmentManager appointmentManager;

    public PatientMenu(Patient patient) {
        this.patient = patient;
    }

    public void displayMenu() {
        PatientRepository patientRepository = new PatientRepository();
        PatientManager patientManager = new PatientManager(patientRepository);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nPatient Menu:");
            System.out.println("1. View Medical Record");
            System.out.println("2. Update Personal Information");
            System.out.println("3. View Available Appointment Slots");
            System.out.println("4. Schedule an Appointment");
            System.out.println("5. Reschedule an Appointment");
            System.out.println("6. Cancel an Appointment");
            System.out.println("7. View Scheduled Appointments");
            System.out.println("8. View Past Appointment Outcome Records");
            System.out.println("9. Logout");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    patientManager.viewRecord(patient);
                    break;
                case 2:
                    updatePersonalInformation(patientManager, sc);
                    break;
                case 3:
                    viewAvailableSlots();
                    break;
                case 4:
                    scheduleAppointment(sc);
                    break;
                case 5:
                    rescheduleAppointment(sc);
                    break;
                case 6:
                    cancelAppointment(sc);
                    break;
                case 7:
                    viewScheduledAppointments();
                    break;
                case 8:
                    viewPastAppointmentOutcomes();
                    break;
                case 9:
                    sc.close();
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Please choose a valid option.");
            }
        }
    }

    private void updatePersonalInformation(PatientManager patientManager, Scanner sc) {
        System.out.println("1. Update email address");
        System.out.println("2. Update phone number");
        System.out.print("Choose an option: ");
        int newInfoChoice = sc.nextInt();
        sc.nextLine();
        switch (newInfoChoice) {
            case 1:
                System.out.print("New email address: ");
                String newAddress = sc.nextLine();
                patientManager.updateEmail(patient, newAddress);
                System.out.println("Email updated successfully.");
                break;
            case 2:
                System.out.print("New phone number: ");
                String newNumber = sc.nextLine();
                patientManager.updatePhoneNumber(patient, newNumber);
                System.out.println("Phone number updated successfully.");
                break;
            default:
                System.out.println("Please choose a valid option (1-2)");
                break;
        }
    }

    private void viewAvailableSlots() {
        List<Appointment> availableSlots = appointmentManager.getAvailableAppointments();
        System.out.println("\nAvailable Appointment Slots:");
        for (Appointment appointment : availableSlots) {
            System.out.println(appointment);
        }
    }

    private void scheduleAppointment(Scanner sc) {
        System.out.print("Enter Doctor ID: ");
        String doctorId = sc.nextLine();
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = sc.nextLine();
        System.out.print("Enter Time (HH:MM): ");
        String time = sc.nextLine();
        System.out.print("Enter Type of Service (e.g., Consultation): ");
        String typeOfService = sc.nextLine();

        Appointment appointment = appointmentManager.scheduleAppointment(patient.getPatientId(), doctorId, LocalDate.parse(date), LocalTime.parse(time), typeOfService);
        System.out.println("Appointment Scheduled: " + appointment);
    }

    private void rescheduleAppointment(Scanner sc) {
        System.out.print("Enter Appointment ID to Reschedule: ");
        int appointmentId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter New Date (YYYY-MM-DD): ");
        String newDate = sc.nextLine();
        System.out.print("Enter New Time (HH:MM): ");
        String newTime = sc.nextLine();

        boolean rescheduled = appointmentManager.rescheduleAppointment(appointmentId, LocalDate.parse(newDate), LocalTime.parse(newTime));
        if (rescheduled) {
            System.out.println("Appointment rescheduled successfully.");
        } else {
            System.out.println("Failed to reschedule appointment. Please check the appointment ID and status.");
        }
    }

    private void cancelAppointment(Scanner sc) {
        System.out.print("Enter Appointment ID to Cancel: ");
        int appointmentId = sc.nextInt();
        sc.nextLine();

        boolean canceled = appointmentManager.cancelAppointment(appointmentId);
        if (canceled) {
            System.out.println("Appointment canceled successfully.");
        } else {
            System.out.println("Failed to cancel appointment. Please check the appointment ID and status.");
        }
    }

    private void viewScheduledAppointments() {
        List<Appointment> scheduledAppointments = appointmentManager.getScheduledAppointmentsForPatient(patient.getPatientId());
        System.out.println("\nScheduled Appointments:");
        for (Appointment appointment : scheduledAppointments) {
            System.out.println(appointment);
        }
    }

    private void viewPastAppointmentOutcomes() {
        List<Appointment> pastAppointments = appointmentManager.getCompletedAppointmentsForPatient(patient.getPatientId());
        System.out.println("\nPast Appointment Outcome Records:");
        for (Appointment appointment : pastAppointments) {
            System.out.println(appointment);
        }
    }
}
