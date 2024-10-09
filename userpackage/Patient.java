package userpackage;

import java.util.Scanner;

import utilitypackage.MedicalRecords;
import utilitypackage.MedicalRecordsManager;

public class Patient extends User {
    private MedicalRecords medicalRecords;

    public Patient(String patientID, String password, MedicalRecords patientRecords) {
        super(patientID, password, "Patient");
        this.medicalRecords = patientRecords;
    }

    @Override
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
                    viewMedicalRecords();
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
    

    public void viewMedicalRecords(){
        medicalRecords.viewRecords();
    }

    public void updateInfo(String newAddress, String newNumber){
        medicalRecords.setEmailAdd(newAddress);
        medicalRecords.setPhoneNum(newNumber);
        MedicalRecordsManager.updateRecords(super.getUsername(), this.medicalRecords);
    }
}
