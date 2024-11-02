import java.util.Scanner;

import controllers.AppointmentManager;
import controllers.PatientManager;
import controllers.StaffManager;
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
        AppointmentManager appointmentManager = new AppointmentManager();
        System.out.println("Please log in to the Hospital Management System");
        System.out.println("----------------------------------------------");

        System.out.print("Are you a patient or staff? (Enter P for Patient, S for Staff): ");
        String userType = sc.nextLine().toUpperCase();
        boolean isPatient = userType.equals("P");
        String hospitalID = null;
        String password = null;
        while (true) { 
            System.out.print("Hospital ID: ");
            hospitalID = sc.nextLine();

            System.out.print("Password: ");
            password = sc.nextLine(); 

            if(LoginManager.authenticateUser(hospitalID, password, isPatient)){
                break;
            }
            System.out.println("Invalid credentials. Please try again.");
        }
        
        //Display correct menu for logged in user
        System.out.println("Login successful! Welcome to the system.");
        if(isPatient){
            PatientRepository patientRepository = new PatientRepository();
            Patient patient = patientRepository.load(hospitalID);
            if (patient.isDefault()){
                System.out.print("Please set a new password: ");
                String newPW = sc.nextLine();
                PatientManager patientManager = new PatientManager(patientRepository);
                patientManager.updatePassword(patient, newPW);
            }
            PatientMenu patientMenu = new PatientMenu(patient);
            patientMenu.displayMenu();
        }
        else{
            StaffRepository staffRepository = new StaffRepository();
            Staff staff = staffRepository.load(hospitalID);
            if (staff.isDefault()){
                System.out.print("Please set a new password: ");
                String newPW = sc.nextLine();
                StaffManager staffManager= new StaffManager(staffRepository);
                staffManager.updatePassword(staff, newPW);
            }

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