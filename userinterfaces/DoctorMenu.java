package userinterfaces;

import java.util.Scanner;

import controllers.PatientManager;
import dbinterfaces.PatientRepository;
import entities.Doctor;
import entities.Patient;

public class DoctorMenu implements IMenu{
    private Doctor doctor;
    private PatientManager patientManager;

    public DoctorMenu(Doctor doctor, PatientManager patientManager){
        this.doctor = doctor;
        this.patientManager = patientManager;
    }

    public void displayMenu(){
        Scanner sc = new Scanner(System.in);
        int choice;
        String patientID;
        do{
            menuItems();
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println();
                    patientManager.viewAllPatients();
                    System.out.println();
                    System.out.print("Input PatientID: ");
                    patientID = sc.nextLine();
                    System.out.println();
                    Patient view = PatientRepository.load(patientID);
                    patientManager.viewRecord(view);
                    System.out.println();
                    break;
                case 2:
                    String newInfo;
                    System.out.println();
                    patientManager.viewAllPatients();
                    System.out.println();
                    System.out.print("Input PatientID: ");
                    patientID = sc.nextLine();
                    System.out.println();
                    Patient edit = PatientRepository.load(patientID);
                    System.out.println("1. Add a new diagnosis");
                    System.out.println("2. Add a new prescription");
                    System.out.println("3. Add a new treatment plan");
                    System.out.println();
                    System.out.print("Choose an option: ");
                    int newInfoChoice = sc.nextInt();
                    sc.nextLine();
                    switch(newInfoChoice){
                        case 1: 
                            System.out.print("Diagnosis: ");
                            newInfo = sc.nextLine();
                            patientManager.addDiagnosis(edit, newInfo);
                            break;
                        case 2:
                            System.out.print("Prescription: ");
                            newInfo = sc.nextLine();
                            patientManager.addMedication(edit, newInfo);
                            break;
                        case 3:
                            System.out.print("Treatment Plan: ");
                            newInfo = sc.nextLine();
                            patientManager.addTreatment(edit, newInfo);
                            break;
                        default: 
                            System.out.println("Please choose a valid option (1-3)");
                            break;
                    }
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

    private void menuItems(){
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

    //For admin to view
    public void viewRecord(){
        this.doctor.viewRecords();
    }
}
