package userinterfaces;

import java.util.Scanner;

import controllers.PatientManager;
import dbinterfaces.PatientRepository;
import entities.Patient;

public class PatientMenu implements IMenu {
    private Patient patient;

    public PatientMenu(Patient patient){
        this.patient = patient;
    }

    public void displayMenu() {
        PatientRepository patientRepository = new PatientRepository();
        PatientManager patientManager = new PatientManager(patientRepository);
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
        System.out.print("Choose an option: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        while(true){
            switch (choice) {
                case 1:
                    PatientManager.viewRecord(patient);
                    break;
                case 2:
                    System.out.println("1. Update email address");
                    System.out.println("2. Update phone number");
                    int newInfoChoice = sc.nextInt();
                    sc.nextLine();
                    switch (newInfoChoice) {
                        case 1:
                            System.out.print("New email address: ");
                            String newAddress = sc.nextLine();
                            patientManager.updateEmail(patient, newAddress);
                            break;
                        case 2:
                            System.out.print("New phone number: ");
                            String newNumber = sc.nextLine();
                            patientManager.updatePhoneNumber(patient, newNumber);
                            break;
                        default:
                            System.out.println("Please choose a valid option (1-2)");
                            break;
                    }
                    break;
                case 9:
                    sc.close();
                    System.out.println("Logging out...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please choose a valid option instead");
                    break;
            }
            System.out.print("Choose another option: ");
            choice = sc.nextInt();
            sc.nextLine();
        }
    }
}
