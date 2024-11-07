package userinterfaces;

import java.util.InputMismatchException;
import java.util.Scanner;

import controllers.AppointmentManager;
import controllers.PatientManager;
import entities.appointments.ApptStatus;

public class PatientMenu implements IMenu {
    private String patientID;
    private PatientManager patientManager;
    private AppointmentManager apptManager;

    public PatientMenu(String patientID, PatientManager patientManager, AppointmentManager apptManager) {
        this.patientID = patientID;
        this.patientManager = patientManager;
        this.apptManager = apptManager;
    }

    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            menuItems();
            try {
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println();
                        viewRecord();
                        System.out.println();
                        break;
                    case 2:
                        System.out.println();
                        updatePersonalInformation(sc);
                        System.out.println();
                        break;
                    case 3:
                        System.out.println();
                        viewAvailable();
                        System.out.println();
                        break;
                    case 4:
                        System.out.println();
                        scheduleAppt(sc);
                        System.out.println();
                        break;
                    case 5:
                        System.out.println();
                        rescheduleAppt(sc);
                        System.out.println();
                        break;
                    case 6:
                        System.out.println();
                        cancelAppt(sc);
                        System.out.println();
                        break;
                    case 7:
                        System.out.println();
                        viewUpcoming();
                        System.out.println();
                        break;
                    case 9:
                        sc.close();
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Please choose a valid option instead (1-9)");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine(); // this is to clear the invalid input
            }
        } while (true);
    }

    private void menuItems() {
        System.out.println();
        System.out.println("=========================================");
        System.out.println("Patient Menu:");
        System.out.println("1. View Medical Record");
        System.out.println("2. Update Personal Information");
        System.out.println("3. View Available Appointment Slots");
        System.out.println("4. Schedule an Appointment");
        System.out.println("5. Reschedule an Appointment");
        System.out.println("6. Cancel an Appointment");
        System.out.println("7. View Scheduled Appointments");
        System.out.println("8. View Past Appointment Outcome Records");
        System.out.println("9. Logout");
        System.out.println("=========================================");
        System.out.println();
        System.out.print("Choose an option: ");
    }

    private void viewRecord() {
        System.out.println("Your Medical Records: ");
        patientManager.viewRecord(patientID);
    }

    private void updatePersonalInformation(Scanner sc) {
        System.out.println("1. Update email address");
        System.out.println("2. Update phone number");
        System.out.println();
        System.out.print("Choose an option: ");
        int newInfoChoice = sc.nextInt();
        sc.nextLine();
        switch (newInfoChoice) {
            case 1:
                System.out.print("New email address: ");
                String newAddress = sc.nextLine();
                patientManager.updateEmail(patientID, newAddress);
                System.out.println("Email updated successfully.");
                break;
            case 2:
                System.out.print("New phone number: ");
                String newNumber = sc.nextLine();
                patientManager.updatePhoneNumber(patientID, newNumber);
                System.out.println("Phone number updated successfully.");
                break;
            default:
                System.out.println("Please choose a valid option (1-2)");
                break;
        }
    }

    private void viewAvailable() {
        System.out.println("All available slots: ");
        apptManager.viewByFilter(ApptStatus.AVAILABLE);
    }

    private void scheduleAppt(Scanner sc) {
        System.out.print("Appointment ID: ");
        String apptID = sc.nextLine();
        apptManager.schedule(apptID, patientID);
    }

    private void rescheduleAppt(Scanner sc) {
        System.out.println("Please input the appointment ID of the appointment you want to reschedule:");
        System.out.print("Appointment ID: ");
        String prevApptID = sc.nextLine();
        System.out.print("Please choose a new appointment slot: ");
        String newApptID = sc.nextLine();
        apptManager.reschedule(prevApptID, newApptID);
    }

    private void cancelAppt(Scanner sc) {
        System.out.println("Please input the appointment you wish to cancel: ");
        System.out.print("Appointment ID: ");
        String cancelledID = sc.nextLine();
        apptManager.cancel(cancelledID);
    }

    private void viewUpcoming() {
        apptManager.viewByFilterPatient(patientID, ApptStatus.CONFIRMED);
    }

}
