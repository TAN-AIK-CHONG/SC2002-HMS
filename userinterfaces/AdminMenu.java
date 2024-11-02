package userinterfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dbinterfaces.InventoryRepository;
import controllers.InventoryManager;
import controllers.AppointmentManager;
import entities.*;

public class AdminMenu implements IMenu {
    private Admin admin;
    private List<Staff> staffList = new ArrayList<>(); //  list to store staff members
    public AdminMenu(Admin admin){
        this.admin = admin;
    }

    public void displayMenu(){
        System.out.println("Administrator Menu");
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. View Appointments details");
        System.out.println("3. View and Manage Medication Inventory");
        System.out.println("4. Approve Replenishment Requests");
        System.out.println("5. Logout");
        System.out.print("Choose an option: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();

        while(true){
            switch (choice) {
                case 1:
                    manageHospitalStaff(sc);
                    break;
                case 2:
                    viewAppointmentDetails();
                    break;
                case 3:
                    List<Medication> inventory = InventoryRepository.load();
                    InventoryManager inventoryManager=new InventoryManager();
                    inventoryManager.viewInventory(inventory);
                    System.out.println("1. Add new medication");
                    System.out.println("2. Remove a medication");
                    System.out.println("3. Update stock level of medication");
                    System.out.println("4. No further action");
                    System.out.print("Choose an option: ");
                    int inventoryAction = sc.nextInt();
                    sc.nextLine();
                    switch(inventoryAction){
                        case 1:
                            System.out.print("Enter new medication: ");
                            String medName = sc.nextLine();
                            System.out.print("Enter initial stock quantity: ");
                            int quantity = sc.nextInt();
                            sc.nextLine(); 
                            System.out.print("Enter low stock alert level: ");
                            int alert = sc.nextInt();
                            sc.nextLine();
                            Medication newMed = new Medication(medName, quantity, alert);
                            inventoryManager.addInventory(inventory, newMed);
                            break;
                        case 2:
                            System.out.print("Enter medication to be removed: ");
                            String removedMed = sc.nextLine();
                            inventoryManager.removeInventory(inventory, removedMed);
                            break;
                        case 3:
                            System.out.print("Enter medication to be updated: ");
                            String updatedMed = sc.nextLine();
                            System.out.print("Enter new stock level: ");
                            int newLevel = sc.nextInt();
                            sc.nextLine();
                            inventoryManager.updateInventory(inventory, updatedMed, newLevel);
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Please choose a valid option (1-3)");
                            break;
                    }
                    InventoryRepository.store(inventory);
                    break;
                case 4:
                    approveReplenishmentRequests(sc);
                    break;
                case 5:
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
    AppointmentManager appointmentManager = new AppointmentManager();
    InventoryManager inventoryManager = new InventoryManager();
    private void viewAppointmentDetails() {
        List<Appointment> allAppointments = appointmentManager.getAllAppointments();
        System.out.println("\nScheduled Appointments:");
        for (Appointment appointment : allAppointments) {
            System.out.println("Appointment ID: " + appointment.getAppointmentId());
            System.out.println("Patient ID: " + appointment.getPatientId());
            System.out.println("Doctor ID: " + appointment.getDoctorId());
            System.out.println("Status: " + appointment.getStatus());
            System.out.println("Date: " + appointment.getDate() + " Time: " + appointment.getTime());

            if (appointment.getStatus().equalsIgnoreCase("completed")) {
                System.out.println("Appointment Outcome Record:");
                System.out.println(" - Type of Service: " + appointment.getTypeOfService());
                System.out.println(" - Notes: " + appointment.getNotes());
                System.out.println(" - Prescriptions:");
                for (var prescription : appointment.getPrescriptions()) {
                    System.out.println("   - " + prescription.getMedicationName() + ": " + prescription.getStatus());
                }
            }
            System.out.println();
        }
    }
    private void approveReplenishmentRequests(Scanner sc) {
        List<Medication> replenishmentRequests = inventoryManager.getReplenishmentRequests();
        if (replenishmentRequests.isEmpty()) {
            System.out.println("No pending replenishment requests.");
        } else {
            System.out.println("Pending Replenishment Requests:");
            for (Medication medication : replenishmentRequests) {
                System.out.println("- " + medication.getName() + ": Request for " + medication.getReplenishmentQuantity() + " units");
            }
            System.out.print("Approve all replenishment requests? (Y/N): ");
            String response = sc.nextLine().toUpperCase();
            if (response.equals("Y")) {
                inventoryManager.approveReplenishmentRequests();
                System.out.println("All replenishment requests have been approved.");
            } else {
                System.out.println("Replenishment requests were not approved.");
            }
        }
    }
    private void manageHospitalStaff(Scanner sc) {
        System.out.println("\nManage Hospital Staff:");
        System.out.println("1. Add New Staff Member");
        System.out.println("2. Update Staff Member");
        System.out.println("3. Remove Staff Member");
        System.out.println("4. Display All Staff Members");
        System.out.print("Choose an option: ");
        int staffChoice = sc.nextInt();
        sc.nextLine();

        switch (staffChoice) {
            case 1:
                System.out.print("Enter User ID: ");
                String userID = sc.nextLine();
                System.out.print("Enter Password: ");
                String password = sc.nextLine();
                System.out.print("Enter Staff Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Role (Doctor/Pharmacist): ");
                String role = sc.nextLine();
                System.out.print("Enter Gender (Male/Female): ");
                Gender gender = Gender.valueOf(sc.nextLine().toUpperCase());
                System.out.print("Enter Age: ");
                int age = sc.nextInt();
                sc.nextLine();

                Staff newStaff = new Staff(userID, password, name, gender, age, role);
                staffList.add(newStaff);
                System.out.println("New staff member added.");
                break;

            case 2:
                System.out.print("Enter Staff ID to Update: ");
                int staffIdToUpdate = sc.nextInt();
                sc.nextLine();
                Staff staffToUpdate = findStaffById(staffIdToUpdate);
                if (staffToUpdate != null) {
                    System.out.print("Enter New Name (leave blank to skip): ");
                    String newName = sc.nextLine();
                    if (!newName.isEmpty()) {
                        staffToUpdate.setName(newName);
                    }

                    System.out.print("Enter New Role (leave blank to skip): ");
                    String newRole = sc.nextLine();
                    if (!newRole.isEmpty()) {
                        staffToUpdate.setRole(newRole);
                    }

                    System.out.print("Enter New Gender (Male/Female, leave blank to skip): ");
                    String newGender = sc.nextLine();
                    if (!newGender.isEmpty()) {
                        try {
                            staffToUpdate.setGender(Gender.valueOf(newGender.toUpperCase()));
                            System.out.println("Gender updated successfully.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid gender input. Please enter 'Male' or 'Female'.");
                        }
                    }

                    System.out.print("Enter New Age (enter -1 to skip): ");
                    int newAge = sc.nextInt();
                    sc.nextLine();
                    if (newAge != -1) {
                        staffToUpdate.setAge(newAge);
                    }
                    System.out.println("Staff member " + staffIdToUpdate + " updated successfully.");
                } else {
                    System.out.println("Staff member with ID " + staffIdToUpdate + " not found.");
                }
                break;
            case 3:
                System.out.print("Enter Staff ID to Remove: ");
                int staffIdToRemove = sc.nextInt();
                sc.nextLine();
                Staff staffToRemove = findStaffById(staffIdToRemove);
                if (staffToRemove != null) {
                    staffList.remove(staffToRemove);
                    System.out.println("Staff member " + staffIdToRemove + " removed successfully.");
                } else {
                    System.out.println("Staff member with ID " + staffIdToRemove + " not found.");
                }
                break;
            case 4:
                System.out.println("\nStaff List:");
                for (Staff staff : staffList) {
                    System.out.println(staff);
                }
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
    private Staff findStaffById(int staffId) {
        String staffIdString = String.valueOf(staffId);//convert int to String for comparison as staffId is a string
        for (Staff staff : staffList) {
            if (staff.getUserID().equals(staffIdString)) {
                return staff;
            }
        }
        return null;
    }
    //For admin to view
    public void viewRecord(){
        this.admin.viewRecords();
    }
}
