import java.util.Scanner;

import userpackage.Doctor;
import userpackage.Patient;
import utilitypackage.LoginManager;
import utilitypackage.MedicalRecords;
import utilitypackage.MedicalRecordsManager;
import utilitypackage.StaffRecords;
import utilitypackage.StaffRecordsManager;

public class HMSmain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please log in to the Hospital Management System");
        System.out.println("----------------------------------------------");

        boolean loggedIn = false;

        System.out.print("Are you a patient or staff? (Enter P for Patient, S for Staff): ");
        String userType = sc.nextLine().toUpperCase();
        boolean isPatient = userType.equals("P");
        String hospitalID = null;
        String password = null;
        while (!loggedIn) { 
            System.out.print("Hospital ID: ");
            hospitalID = sc.nextLine();

            System.out.print("Password: ");
            password = sc.nextLine(); 

            
            loggedIn = LoginManager.authenticateUser(hospitalID, password, isPatient);
            if(loggedIn){
                break;
            }
            System.out.println("Invalid credentials. Please try again.");
        }
        
        //Display correct menu for logged in user
        System.out.println("Login successful! Welcome to the system.");
        if(isPatient){
            MedicalRecords ownRecord = MedicalRecordsManager.loadRecords(hospitalID);
            Patient newPatient = new Patient(hospitalID, password, ownRecord);
            newPatient.displayMenu();
        }
        else{
            StaffRecords staffInfo = StaffRecordsManager.loadRecords(hospitalID);
            String role = staffInfo.getRole().toLowerCase();
            switch (role){
                case "doctor":
                    Doctor newDoc = new Doctor(hospitalID, password);
                    newDoc.displayMenu();
                    break;
                case "pharmacist":
                    break;
                case "administrator":
                    break;
            }
        }
        sc.close();

    }
}