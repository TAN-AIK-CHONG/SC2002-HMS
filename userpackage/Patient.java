package userPackage;

import java.util.Scanner;

import DBManagers.PatientRecManager;
import records.PatientRecord;

public class Patient implements IMenu {
    private PatientRecord record;

    public Patient(PatientRecord record){
        this.record = record;
    }

    public void updateInfo(String newAddress, String newNumber){
        this.record.setEmailAdd(newAddress);
        this.record.setPhoneNum(newNumber);
        PatientRecManager.store(record.getPatientID(), record);
    }

    public void displayMenu() {
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
                    this.record.view();
                    break;
                case 2:
                    System.out.println("New email address: ");
                    String newAddy = sc.nextLine();
                    System.out.println("New phone number: ");
                    String newNumber = sc.nextLine();
                    updateInfo(newAddy, newNumber);
                    break;
                case 9:
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
}
