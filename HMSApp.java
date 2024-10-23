import java.util.Scanner;

import utility.LoginManager;
import DBManagers.PatientRecManager;
import DBManagers.StaffRecManager;
import records.PatientRecord;
import records.StaffRecord;
import userPackage.Patient;
import userPackage.Pharmacist;
import userPackage.Admin;
import userPackage.Doctor;


public class HMSApp {
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
            PatientRecord PRecord = PatientRecManager.load(hospitalID);
            Patient newPatient = new Patient(PRecord);
            newPatient.displayMenu();
        }
        else{
            StaffRecord staffInfo = StaffRecManager.load(hospitalID);
            String role = staffInfo.getRole().toLowerCase();
            switch (role){
                case "doctor":
                    Doctor newDoc = new Doctor(staffInfo);
                    newDoc.displayMenu();
                    break;
                case "pharmacist":
                    Pharmacist newPharma = new Pharmacist(staffInfo);
                    newPharma.displayMenu();
                    break;
                case "administrator":
                    Admin newAdmin = new Admin(staffInfo);
                    newAdmin.displayMenu();
                    break;
            }
        }
        sc.close();
        //some changes abc123
    }
}