package userinterfaces;

import controllers.AppointmentManager;
import controllers.InventoryManager;
import controllers.PatientManager;
import entities.appointments.ApptPrescription;
import entities.appointments.ApptStatus;
import entities.appointments.TypeOfService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
            try{
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
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Please choose a valid option  (1-8)");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine();
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
                    System.out.println("Error: Medication does not exist in the database.");
                    break;
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
        System.out.println("Here is your schedule:");
        apptManager.viewByFilterDoc(doctorID, ApptStatus.AVAILABLE);
        apptManager.viewByFilterDoc(doctorID, ApptStatus.CONFIRMED);
    }

    private void chooseAppointment(Scanner sc) {
        System.out.println("Pending appointments: ");
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
        System.out.println("Confirmed appointments: ");
        apptManager.viewByFilterDoc(doctorID, ApptStatus.CONFIRMED);
        System.out.println();
        System.out.print("Enter Appointment ID: ");
        String apptID = sc.nextLine().toUpperCase();
    
        System.out.println("Types of Service:");
        System.out.println("1. Consultation");
        System.out.println("2. Blood Test");
        System.out.println("3. X-Ray");
        System.out.println("4. Physical Therapy");
        System.out.println("5. Vaccination");
        System.out.println("6. EKG");
        System.out.println();
    
        TypeOfService serviceTypeInput = null; 
    
        while (serviceTypeInput == null) {
            try {
                System.out.print("Choose service: ");
                int tos = sc.nextInt();
                sc.nextLine(); 
                switch (tos) {
                    case 1:
                        serviceTypeInput = TypeOfService.CONSULTATION;
                        break;
                    case 2:
                        serviceTypeInput = TypeOfService.BLOODTEST;
                        break;
                    case 3:
                        serviceTypeInput = TypeOfService.XRAY;
                        break;
                    case 4:
                        serviceTypeInput = TypeOfService.PHYSICALTHERAPY;
                        break;
                    case 5:
                        serviceTypeInput = TypeOfService.VACCINATION;
                        break;
                    case 6:
                        serviceTypeInput = TypeOfService.EKG;
                        break;
                    default:
                        System.out.println("Please choose a valid option (1-6).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine();
            }
        }
    
        System.out.print("Enter Consultation Notes: ");
        String consultationNotes = sc.nextLine().toUpperCase();
    
        Double apptBill = null;
        while (apptBill == null) {
            System.out.println("Enter bill for appointment: ");
            String input = sc.nextLine();
            try {
                apptBill = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    
        List<ApptPrescription> prescriptions = new ArrayList<>();
        String prescriptionName;
    
        inventoryManager.viewInventory();
        System.out.println();
        while (true) {
            System.out.print("Enter prescription (x to cancel): ");
            prescriptionName = sc.nextLine();
            if (prescriptionName.equals("x")) {
                break;
            }
            if (inventoryManager.doesMedicationExist(prescriptionName)) {
                ApptPrescription currentPrescription = new ApptPrescription(prescriptionName);
                prescriptions.add(currentPrescription);
            } else {
                System.out.println("Error: Medication does not exist in the database. Try again.");
            }
        }
    
        apptManager.makeAOR(apptID, consultationNotes, serviceTypeInput, prescriptions, apptBill);
        String patientID = apptManager.getPatientIDfromApptID(apptID);
        patientManager.updateBill(patientID, apptBill);
    }
    
}
