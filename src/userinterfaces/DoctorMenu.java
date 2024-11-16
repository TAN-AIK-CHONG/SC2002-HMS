package userinterfaces;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import controllers.InventoryManager;
import controllers.AppointmentManager;
import controllers.PatientManager;
import entities.appointments.AOR;
import entities.appointments.ApptPrescription;
import entities.appointments.TypeOfService;
import entities.appointments.ApptSlot;
import entities.appointments.ApptStatus;
import filehandlers.AORRepository;
import filehandlers.ApptSlotRepository;

public class DoctorMenu implements IMenu {
    private String doctorID;
    private PatientManager patientManager;
    private AppointmentManager apptManager;
    private InventoryManager inventoryManager;

    public DoctorMenu(String doctorID, PatientManager patientManager, AppointmentManager apptManager,
            InventoryManager inventoryManager) {
        this.doctorID = doctorID;
        this.patientManager = patientManager;
        this.apptManager = apptManager;
        this.inventoryManager = inventoryManager;
    }

    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            menuItems();
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println();
                    viewPatientRecord(sc);
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    updatePatientRecord(sc);
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    viewPersonalSchedule();
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    setAvailableAppts(sc);
                    System.out.println();
                    break;
                case 5:
                    System.out.println();
                    chooseAppointment(sc);
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    viewUpcomingAppts();
                    System.out.println();
                    break;
                case 7:
                    System.out.println();
                    recordAppointmentOutcome(sc);
                    System.out.println();
                    break;
                case 8:
                    sc.close();
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Please choose a valid option  (1-8)");
                    break;
            }
        } while (true);
    }

    private void menuItems() {
        System.out.println();
        System.out.println("=========================================");
        System.out.println("Doctor Menu");
        System.out.println("1. View Patient Medical Record");
        System.out.println("2. Update Patient Medical Record");
        System.out.println("3. View Personal Schedule");
        System.out.println("4. Set Availability for Appointments");
        System.out.println("5. Accept or Decline Appointment Requests");
        System.out.println("6. View Upcoming Appointments");
        System.out.println("7. Record Appointment Outcome");
        System.out.println("8: Logout");
        System.out.println("=========================================");
        System.out.println();
        System.out.print("Choose an option: ");
    }

    private void viewPatientRecord(Scanner sc) {
        patientManager.viewAllPatients();
        System.out.println();
        System.out.print("Input PatientID: ");
        String patientID = sc.nextLine().toUpperCase();
        System.out.println();
        patientManager.viewRecord(patientID);
    }

    private void updatePatientRecord(Scanner sc) {
        String newInfo;

        System.out.println();
        patientManager.viewAllPatients();
        System.out.println();
        System.out.print("Input PatientID: ");
        String patientID = sc.nextLine().toUpperCase();
        System.out.println();
        System.out.println("1. Add a new diagnosis");
        System.out.println("2. Add a new prescription");
        System.out.println("3. Add a new treatment plan");
        System.out.println();
        System.out.print("Choose an option: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.print("Diagnosis: ");
                newInfo = sc.nextLine().toUpperCase();
                patientManager.addDiagnosis(patientID, newInfo);
                break;
            case 2:
                inventoryManager.viewInventory();
                System.out.println();
                System.out.print("Prescription: ");
                newInfo = sc.nextLine().toUpperCase();
                if (inventoryManager.doesMedicationExist(newInfo)) {
                    patientManager.addMedication(patientID, newInfo);
                } else {
                    System.out.println("Error: Medication does not exist in the database. Try again.");
                }

                break;
            case 3:
                System.out.print("Treatment Plan: ");
                newInfo = sc.nextLine().toUpperCase();
                patientManager.addTreatment(patientID, newInfo);
                break;
            default:
                System.out.println("Please choose a valid option (1-3)");
                break;
        }
    }

    private void setAvailableAppts(Scanner sc) {
        System.out.print("Please input the available date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(sc.nextLine());
        System.out.print("Please choose the time slot (HH:MM): ");
        LocalTime time = LocalTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("HH:mm"));
        apptManager.setAvailable(doctorID, date, time);
    }

    private void viewUpcomingAppts() {
        System.out.println("Here are your upcoming confirmed appointments:");
        apptManager.viewByFilterDoc(doctorID, ApptStatus.CONFIRMED);
    }

    private void viewPersonalSchedule() {
        System.out.println("Here are the slots you have made available:");
        apptManager.viewByFilterDoc(doctorID, ApptStatus.AVAILABLE);
    }

    private void chooseAppointment(Scanner sc) {
        apptManager.viewByFilterDoc(doctorID, ApptStatus.PENDING);
        System.out.println();
        System.out.print("Input Appointment ID ");
        String apptID = sc.nextLine().toUpperCase();
        System.out.println("1. Accept Appointment");
        System.out.println("2. Decline Appointment");
        System.out.println();
        System.out.print("Choose an option: ");
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                apptManager.handlePending(apptID, ApptStatus.CONFIRMED);
                break;
            case 2:
                apptManager.handlePending(apptID, ApptStatus.CANCELLED);
                break;
            default:
                System.out.println("Please choose a valid option (1-2)");
                break;
        }

    }

    private void recordAppointmentOutcome(Scanner sc) {
        System.out.print("Enter Appointment ID: ");
        String apptID = sc.nextLine().toUpperCase();

        System.out.print("Enter Type of Service (e.g., CONSULTATION, FOLLOW_UP): ");
        TypeOfService serviceTypeInput = TypeOfService.fromString(sc.nextLine());

        System.out.print("Enter Consultation Notes: ");
        String consultationNotes = sc.nextLine();

        List<ApptPrescription> prescriptions = new ArrayList<>();
        String prescriptionName;

        inventoryManager.viewInventory();
        System.out.println();
        while (true) {
            System.out.print("Enter prescription (-1 to cancel): ");
            prescriptionName = sc.nextLine();
            if (prescriptionName.equals("-1")) {
                break;
            }
            if (inventoryManager.doesMedicationExist(prescriptionName)) {
                ApptPrescription currentPrescription = new ApptPrescription(prescriptionName);
                prescriptions.add(currentPrescription);
            } else {
                System.out.println("Error: Medication does not exist in the database. Try again.");
            }
        }

        apptManager.makeAOR(apptID, consultationNotes, serviceTypeInput, prescriptions);

    }

}
