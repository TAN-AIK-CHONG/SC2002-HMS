package userinterfaces;

import controllers.AppointmentManager;
import controllers.InventoryManager;
import controllers.PatientManager;
import controllers.StaffManager;
import entities.Gender;
import entities.Medication;
import entities.Patient;
import entities.appointments.ApptSlot;
import entities.appointments.ApptStatus;
import filehandlers.ApptSlotRepository;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminMenu implements IMenu {
    private String adminID;
    private InventoryManager inventoryManager;
    private StaffManager staffManager;
    private AppointmentManager appointmentManager;

    public AdminMenu(String adminID, InventoryManager inventoryManager, StaffManager staffManager,
            AppointmentManager appointmentManager) {
        this.adminID = adminID;
        this.inventoryManager = inventoryManager;
        this.staffManager = staffManager;
        this.appointmentManager = appointmentManager;
    }

    public void displayMenu() {
        
        Scanner sc = new Scanner(System.in);
        int choice;
        
        do {
            menuItems();
            try{
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        manageHospitalStaff(sc);
                        break;
                    case 2:
                        viewAppointmentDetails(sc);
                        break;
                    case 3:
                        manageMedication(sc);
                        break;
                    case 4:
                        approveRequest(sc);
                        break;
                    case 5:
                        sc.close();
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Please choose a valid option  (1-5)");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine();
            }
        } while (true);        
    }

    private void menuItems(){
        System.out.println();
        System.out.println("========================================");
        System.out.println("Administrator Menu");
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. View Appointments details");
        System.out.println("3. View and Manage Medication Inventory");
        System.out.println("4. Approve Replenishment Requests");
        System.out.println("5. Logout");
        System.out.println("========================================");
        System.out.println();
        System.out.print("Choose an option: ");
    }

    private void manageHospitalStaff(Scanner sc) {
        System.out.println();
        System.out.println("=========================================");
        System.out.println("Manage Hospital Staff:");
        System.out.println("1. Add New Staff Member");
        System.out.println("2. Update Staff Member");
        System.out.println("3. Remove Staff Member");
        System.out.println("4. Display All Staff Members");
        System.out.println("=========================================");
        System.out.println();
        System.out.print("Choose an option: ");
        int staffChoice = sc.nextInt();
        sc.nextLine();

        switch (staffChoice) {
            case 1:
                System.out.print("Enter User ID: ");
                String staffID = sc.nextLine().toUpperCase();
                System.out.print("Enter Password: ");
                String password = sc.nextLine();
                System.out.print("Enter Staff Name: ");
                String name = sc.nextLine().toUpperCase();
                System.out.print("Enter Role: ");
                String role = sc.nextLine().toUpperCase();
                System.out.print("Enter Gender (Male/Female): ");
                Gender gender = Gender.fromString(sc.nextLine());
                System.out.print("Enter Age: ");
                int age = sc.nextInt();
                sc.nextLine();

                staffManager.addStaff(staffID, password, name, role, gender, age);
                System.out.println("New staff member added.");
                break;

            case 2:
                staffManager.viewStaffList();
                System.out.print("Enter Staff ID to Update: ");
                String staffIdToUpdate = sc.nextLine().toUpperCase();

                System.out.print("Enter Role: ");
                String newRole = sc.nextLine().toUpperCase();

                System.out.print("Enter Gender (Male/Female): ");
                String newGender = sc.nextLine().toUpperCase();

                System.out.print("Enter Age: ");
                int newAge = sc.nextInt();
                sc.nextLine();

                staffManager.updateStaff(staffIdToUpdate, newRole, newGender, newAge);

                System.out.println("Staff member " + staffIdToUpdate + " updated successfully.");
                break;
            case 3:
                staffManager.viewStaffList();
                System.out.print("Enter Staff ID to Remove: ");
                String staffIdToRemove = sc.nextLine().toUpperCase();
                staffManager.removeStaff(staffIdToRemove);
                break;
            case 4:
                System.out.println();
                System.out.println("Staff List:");
                staffManager.viewAllStaffDetails();
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }

    public void approveRequest(Scanner sc) {
        inventoryManager.viewRequests();
        System.out.println();
        System.out.print("Enter medication name to approve request: ");
        String medName = sc.nextLine().toUpperCase();
        inventoryManager.approveRequest(medName);
    }

    public void viewAppointmentDetails(Scanner sc) {
        System.out.println();
        System.out.println("=========================================");
        System.out.println("View Appointment Details");
        System.out.println("Choose an option to filter appointments:");
        System.out.println("1. View confirmed appointments");
        System.out.println("2. View cancelled appointments");
        System.out.println("3. View completed appointments");
        System.out.println("4. View all appointments");
        System.out.println("=========================================");
        System.out.println();
        System.out.print("Choose an option: ");
        int filterChoice = sc.nextInt();
        sc.nextLine();

        ApptStatus status = null;

        switch (filterChoice) {
            case 1:
                status = ApptStatus.CONFIRMED;
                break;
            case 2:
                status = ApptStatus.CANCELLED;
                break;
            case 3:
                status = ApptStatus.COMPLETED;
                break;
            case 4:
                status = null;
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }
        if (status == null) {
            List<ApptSlot> slots = ApptSlotRepository.load();
            for (ApptSlot appointment : slots) {
                appointment.view();
                System.out.println("---------------------------");
            }
        } else {
            appointmentManager.viewByFilter(status);
        }
    }

    private void manageMedication(Scanner sc){
        System.out.println();
        inventoryManager.viewInventory();
        System.out.println();
        System.out.println("1. Add new medication");
        System.out.println("2. Remove a medication");
        System.out.println("3. Update stock level of medication");
        System.out.println("4. No further action");
        System.out.println();
        System.out.print("Choose an option: ");
        int inventoryAction = sc.nextInt();
        sc.nextLine();
        switch (inventoryAction) {
            case 1:
                System.out.print("Enter new medication: ");
                String medName = sc.nextLine().toUpperCase();
                System.out.print("Enter initial stock quantity: ");
                int quantity = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter low stock alert level: ");
                int alert = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Original level: ");
                int original = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Request: ");
                boolean request = sc.nextBoolean();
                Medication newMed = new Medication(medName, quantity, alert, original, request);
                inventoryManager.addInventory(newMed);
                break;
            case 2:
                System.out.print("Enter medication to be removed: ");
                String removedMed = sc.nextLine().toUpperCase();
                inventoryManager.removeInventory(removedMed);
                break;
            case 3:
                System.out.print("Enter medication to be updated: ");
                String updatedMed = sc.nextLine().toUpperCase();
                System.out.print("Enter new stock level: ");
                int newLevel = sc.nextInt();
                sc.nextLine();
                inventoryManager.updateInventory(updatedMed, newLevel);
                break;
            case 4:
                break;
            default:
                System.out.println("Please choose a valid option (1-3)");
                break;
        }
    }

}
