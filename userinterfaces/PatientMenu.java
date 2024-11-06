package userinterfaces;

import java.util.Scanner;

import controllers.PatientManager;
import entities.Patient;

public class PatientMenu implements IMenu {
    private Patient patient;
    private PatientManager patientManager;

    public PatientMenu(Patient patient, PatientManager patientManager){
        this.patient = patient;
        this.patientManager = patientManager;
    }

    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            menuItems();
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println();
                    patientManager.viewRecord(patient);
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    updatePersonalInformation(sc);
                    System.out.println();
                    break;
                case 9:
                    sc.close();
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Please choose a valid option instead");
                    break;
            }
        } while(true);
    }

    private void menuItems(){
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

}
