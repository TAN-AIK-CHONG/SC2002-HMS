import java.util.Scanner;

import dbinterfaces.PatientRepository;
import dbinterfaces.StaffRepository;
import utility.LoginManager;
import entities.Patient;
import entities.Pharmacist;
import entities.Doctor;
import entities.Staff;
import entities.Admin;
import userinterfaces.AdminMenu;
import userinterfaces.DoctorMenu;
import userinterfaces.PatientMenu;
import userinterfaces.PharmacistMenu;


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
            Patient patient = PatientRepository.load(hospitalID);
            PatientMenu patientMenu = new PatientMenu(patient);
            patientMenu.displayMenu();
        }
        else{
            Staff staff = StaffRepository.load(hospitalID);

            if (staff instanceof Doctor){
                Doctor doctor = (Doctor) staff;
                DoctorMenu docMenu = new DoctorMenu(doctor);
                docMenu.displayMenu();
            }
            else if (staff instanceof Pharmacist){
                Pharmacist Pharma = (Pharmacist) staff;
                PharmacistMenu pharmaMenu = new PharmacistMenu(Pharma);
                pharmaMenu.displayMenu();
            }
            else if (staff instanceof Admin){
                Admin admin = (Admin) staff;
                AdminMenu adminMenu = new AdminMenu(admin);
                adminMenu.displayMenu();
            }
        }
        sc.close();

    }
}