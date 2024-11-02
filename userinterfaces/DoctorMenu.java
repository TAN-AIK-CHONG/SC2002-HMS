package userinterfaces;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

import controllers.PatientManager;
import dbinterfaces.PatientRepository;
import entities.Appointment;
import entities.Doctor;
import entities.Patient;
import controllers.AppointmentManager;
import entities.User;

public class DoctorMenu implements IMenu{
    private Doctor doctor;
    private AppointmentManager appointmentManager;

    public DoctorMenu(Doctor doctor) {
        this.doctor = doctor;
        this.appointmentManager = appointmentManager;
    }
    public void displayMenu(){
        System.out.println("Doctor Menu");
        System.out.println("1. View Patient Medical Record");
        System.out.println("2. Update Patient Medical Record");
        System.out.println("3. View Personal Schedule");
        System.out.println("4. Set Availability for Appointments");
        System.out.println("5. Accept or Decline Appointment Requests");
        System.out.println("6. View Upcoming Appointments");
        System.out.println("7. Record Appointment Outcome");
        System.out.println("8: Logout");
        System.out.print("Choose an option: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();

        String patientID;
        PatientRepository patientRepository = new PatientRepository();
        PatientManager patientManager = new PatientManager(patientRepository);
        while(true){
            switch (choice) {
                case 1:
                    System.out.println("Input PatientID: ");
                    patientID = sc.nextLine();
                    Patient view = patientRepository.load(patientID);
                    PatientManager.viewRecord(view);
                    break;
                case 2:
                    String newInfo;
                    System.out.println("Input PatientID: ");
                    patientID = sc.nextLine();
                    Patient edit = patientRepository.load(patientID);
                    System.out.println("1. Add a new diagnosis");
                    System.out.println("2. Add a new prescription");
                    System.out.println("3. Add a new treatment plan");
                    System.out.print("Choose an option: ");
                    int newInfoChoice = sc.nextInt();
                    sc.nextLine();
                    switch(newInfoChoice){
                        case 1: 
                            System.out.println("Diagnosis:");
                            newInfo = sc.nextLine();
                            patientManager.addDiagnosis(edit, newInfo);
                            break;
                        case 2:
                            System.out.println("Prescription:");
                            newInfo = sc.nextLine();
                            patientManager.addMedication(edit, newInfo);
                            break;
                        case 3:
                            System.out.println("Treatment Plan:");
                            newInfo = sc.nextLine();
                            patientManager.addTreatment(edit, newInfo);
                            break;
                        default: 
                            System.out.println("Please choose a valid option (1-3)");
                            break;
                    }
                    break;
                case 3:
                    viewPersonalSchedule();
                    break;
                case 4:
                    setAvailability(sc);
                    break;
                case 5:
                    manageAppointmentRequests(sc);
                    break;
                case 6:
                    viewUpcomingAppointments();
                    break;
                case 7:
                    recordAppointmentOutcome(sc);
                    break;
                case 8:
                    sc.close();
                    System.out.println("Logging out...");
                    System.exit(0);
                default:
                    System.out.println("Please choose a valid option instead");
                    break;
            }
            System.out.print("Choose another option: ");
            choice = sc.nextInt();
            sc.nextLine();
        }
    }
    private void viewPersonalSchedule() {
        List<Appointment> schedule = appointmentManager.getUpcomingAppointmentsForDoctor(doctor.getUserID());
        System.out.println("\nPersonal Schedule:");
        for (Appointment appointment : schedule) {
            System.out.println(appointment);
        }
    }

    private void setAvailability(Scanner sc) {
        System.out.print("Enter available date (YYYY-MM-DD): ");
        String date = sc.nextLine();
        System.out.print("Enter available time (HH:MM): ");
        String time = sc.nextLine();
        System.out.println("Availability set for " + date + " at " + time);
    }

    private void manageAppointmentRequests(Scanner sc) {
        List<Appointment> pendingRequests = appointmentManager.getPendingAppointmentsForDoctor(doctor.getUserID());
        System.out.println("\nPending Appointment Requests:");
        for (Appointment appointment : pendingRequests) {
            System.out.println(appointment);
            System.out.print("Accept appointment (Y/N)? ");
            String response = sc.nextLine().toUpperCase();
            if (response.equals("Y")) {
                appointmentManager.updateAppointmentStatus(appointment.getAppointmentId(), "confirmed");
                System.out.println("Appointment confirmed.");
            } else {
                appointmentManager.updateAppointmentStatus(appointment.getAppointmentId(), "canceled");
                System.out.println("Appointment declined.");
            }
        }
    }

    private void viewUpcomingAppointments() {
        List<Appointment> upcomingAppointments = appointmentManager.getUpcomingAppointmentsForDoctor(doctor.getUserID());
        System.out.println("\nUpcoming Appointments:");
        for (Appointment appointment : upcomingAppointments) {
            System.out.println(appointment);
        }
    }

    private void recordAppointmentOutcome(Scanner sc) {
        System.out.print("Enter Appointment ID to record outcome: ");
        int appointmentId = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter type of service provided (e.g., consultation): ");
        String serviceType = sc.nextLine();

        System.out.print("Enter consultation notes: ");
        String notes = sc.nextLine();

        System.out.print("Enter medication prescribed (separate by commas if multiple): ");
        String[] medications = sc.nextLine().split(",");

        appointmentManager.recordAppointmentOutcome(appointmentId, serviceType, notes, List.of(medications));
        System.out.println("Outcome recorded successfully.");
    }

    // For admin to view
    public void viewRecord() {
        this.doctor.viewRecords();
    }
}
