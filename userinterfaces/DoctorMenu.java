package userinterfaces;

import java.util.Scanner;

import controllers.PatientManager;
import dbinterfaces.PatientRepository;
import entities.Doctor;
import entities.Patient;

public class DoctorMenu implements IMenu{
    private Doctor doctor;

    public DoctorMenu(Doctor doctor){
        this.doctor = doctor;
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
        while(true){
            switch (choice) {
                case 1:
                    System.out.println("Input PatientID: ");
                    patientID = sc.nextLine();
                    Patient view = PatientRepository.load(patientID);
                    PatientManager.viewRecord(view);
                    break;
                case 2:
                    String newInfo;
                    System.out.println("Input PatientID: ");
                    patientID = sc.nextLine();
                    Patient edit = PatientRepository.load(patientID);
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
                            PatientManager.addDiagnosis(edit, newInfo);
                            break;
                        case 2:
                            System.out.println("Prescription:");
                            newInfo = sc.nextLine();
                            PatientManager.addMedication(edit, newInfo);
                            break;
                        case 3:
                            System.out.println("Treatment Plan:");
                            newInfo = sc.nextLine();
                            PatientManager.addTreatment(edit, newInfo);
                            break;
                        default: 
                            System.out.println("Please choose a valid option (1-3)");
                            break;
                    }
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

    //For admin to view
    public void viewRecord(){
        this.doctor.viewRecords();
    }
}
