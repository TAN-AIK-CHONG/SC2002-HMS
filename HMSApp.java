import controllers.AppointmentManager;
import controllers.InventoryManager;
import controllers.PatientManager;
import controllers.StaffManager;
import entities.Admin;
import entities.Doctor;
import entities.Patient;
import entities.Pharmacist;
import entities.Staff;
import filehandlers.PatientRepository;
import filehandlers.StaffRepository;
import java.util.Scanner;
import userinterfaces.AdminMenu;
import userinterfaces.DoctorMenu;
import userinterfaces.PatientMenu;
import userinterfaces.PharmacistMenu;
import utility.LoginManager;


public class HMSApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
        System.out.println("Login successful! Welcome to the system, " + hospitalID);

        InventoryManager inventoryManager = new InventoryManager();
        PatientManager patientManager = new PatientManager();
        StaffManager staffManager = new StaffManager();
        AppointmentManager appointmentManager = new AppointmentManager();


        if(isPatient){
            Patient patient = PatientRepository.load(hospitalID);
            if (patient.isDefault()){
                System.out.println("This is your first login. Please update your password.");
                System.out.print("New password: ");
                String newPW = sc.nextLine();
                PatientManager.updatePassword(patient, newPW);
            }
            PatientMenu patientMenu = new PatientMenu(patient, patientManager, appointmentManager);
            patientMenu.displayMenu();
        }
        else{
            Staff staff = StaffRepository.load(hospitalID);
            if (staff.isDefault()){
                System.out.println("This is your first login. Please update your password.");
                System.out.print("New password: ");
                String newPW = sc.nextLine();
                StaffManager.updatePassword(staff, newPW);
            }

            if (staff instanceof Doctor){
                Doctor doctor = (Doctor) staff;
                DoctorMenu docMenu = new DoctorMenu(doctor, patientManager, appointmentManager);
                docMenu.displayMenu();
            }
            else if (staff instanceof Pharmacist){
                Pharmacist Pharma = (Pharmacist) staff;
                PharmacistMenu pharmaMenu = new PharmacistMenu(Pharma, inventoryManager);
                pharmaMenu.displayMenu();
            }
            else if (staff instanceof Admin){
                Admin admin = (Admin) staff;
                AdminMenu adminMenu = new AdminMenu(admin, inventoryManager, staffManager);
                adminMenu.displayMenu();
            }
        }
        sc.close();
        System.out.println("program exit");
    }
}