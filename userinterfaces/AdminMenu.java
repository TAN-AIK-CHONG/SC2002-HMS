package userinterfaces;

import java.util.Scanner;

import controllers.InventoryManager;
import controllers.StaffManager;
import entities.Admin;
import entities.Gender;
import entities.Medication;

public class AdminMenu implements IMenu {
    private Admin admin;
    private InventoryManager inventoryManager;
    private StaffManager staffManager;

    public AdminMenu(Admin admin, InventoryManager inventoryManager, StaffManager staffManager) {
        this.admin = admin;
        this.inventoryManager = inventoryManager;
        this.staffManager = staffManager;
    }

    public void displayMenu() {
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
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();

        while (true) {
            switch (choice) {
                case 1:
                    manageHospitalStaff(sc);
                    break;
                case 2:
                    break;
                case 3:
                    inventoryManager.viewInventory();
                    System.out.println("1. Add new medication");
                    System.out.println("2. Remove a medication");
                    System.out.println("3. Update stock level of medication");
                    System.out.println("4. No further action");
                    System.out.print("Choose an option: ");
                    int inventoryAction = sc.nextInt();
                    sc.nextLine();
                    switch (inventoryAction) {
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
                            inventoryManager.addInventory(newMed);
                            break;
                        case 2:
                            System.out.print("Enter medication to be removed: ");
                            String removedMed = sc.nextLine();
                            inventoryManager.removeInventory(removedMed);
                            break;
                        case 3:
                            System.out.print("Enter medication to be updated: ");
                            String updatedMed = sc.nextLine();
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
                    break;
                case 4:
                    break;
                case 5:
                    sc.close();
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Please choose a valid option instead");
                    break;
            }
            System.out.print("Choose another option: ");
            choice = sc.nextInt();
            sc.nextLine();
        }
    }

    private void manageHospitalStaff(Scanner sc) {
        System.out.println();
        System.out.println("Manage Hospital Staff:");
        System.out.println("1. Add New Staff Member");
        System.out.println("2. Update Staff Member");
        System.out.println("3. Remove Staff Member");
        System.out.println("4. Display All Staff Members");
        System.out.println();
        System.out.print("Choose an option: ");
        int staffChoice = sc.nextInt();
        sc.nextLine();

        switch (staffChoice) {
            case 1:
                System.out.print("Enter User ID: ");
                String staffID = sc.nextLine();
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

                staffManager.addStaff(staffID, password, name, role, gender, age);
                System.out.println("New staff member added.");
                break;

            case 2:
                System.out.print("Enter Staff ID to Update: ");
                String staffIdToUpdate = sc.nextLine();

                System.out.print("Enter Role: ");
                String newRole = sc.nextLine();

                System.out.print("Enter Gender (Male/Female): ");
                String newGender = sc.nextLine();

                System.out.print("Enter Age: ");
                int newAge = sc.nextInt();
                sc.nextLine();

                staffManager.updateStaff(staffIdToUpdate, newRole, newGender, newAge);

                System.out.println("Staff member " + staffIdToUpdate + " updated successfully.");

                break;
            case 3:
                System.out.print("Enter Staff ID to Remove: ");
                String staffIdToRemove = sc.nextLine();
                staffManager.removeStaff(staffIdToRemove);
                break;
            case 4:
                System.out.println();
                System.out.println("Staff List:");
                staffManager.viewAllStaff();
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }

    // For admin to view
    public void viewRecord() {
        this.admin.viewRecords();
    }
}
