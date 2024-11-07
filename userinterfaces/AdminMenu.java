package userinterfaces;

import controllers.InventoryManager;
import controllers.StaffManager;
import entities.Admin;
import entities.Gender;
import entities.Medication;
import java.util.InputMismatchException;
import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
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

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        manageHospitalStaff(sc);
                        break;
                    case 2:
                        break;
                    case 3:
                        handleInventoryManagement(sc);
                        break;
                    case 4:
                        approveRequest(sc);
                        break;
                    case 5:
                        sc.close();
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Please choose a valid option (1-5) instead");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }
    }

    private void handleInventoryManagement(Scanner sc) {
        try {
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
                    System.out.print("Enter Original level: ");
                    int original = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Request (true/false): ");
                    boolean request = sc.nextBoolean();
                    sc.nextLine();

                    Medication newMed = new Medication(medName, quantity, alert, original, request);
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
                    System.out.println("Please choose a valid option (1-4)");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numbers where required.");
            sc.nextLine(); // Clear invalid input
        }
    }

    private void manageHospitalStaff(Scanner sc) {
        int staffChoice = -1;
        boolean validInput = false;

        System.out.println();
        System.out.println("Manage Hospital Staff:");
        System.out.println("1. Add New Staff Member");
        System.out.println("2. Update Staff Member");
        System.out.println("3. Remove Staff Member");
        System.out.println("4. Display All Staff Members");
        System.out.println();

        // Prompt for option with InputMismatchException handling
        while (!validInput) {
            try {
                System.out.print("Choose an option: ");
                staffChoice = sc.nextInt();
                sc.nextLine(); // Clear the buffer
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                sc.nextLine(); // Clear the invalid input
            }
        }

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

                Gender gender = null;
                boolean validGender = false;

                while (!validGender) {
                    try {
                        gender = Gender.valueOf(sc.nextLine().toUpperCase());
                        validGender = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid gender. Please enter 'Male' or 'Female'.");
                    }
                }

                int age = -1;
                boolean validAge = false;

                while (!validAge) {
                    try {
                        System.out.print("Enter Age: ");
                        age = sc.nextInt();
                        sc.nextLine(); // Clear the buffer
                        validAge = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid age (integer).");
                        sc.nextLine(); // Clear the invalid input
                    }
                }

                staffManager.addStaff(staffID, password, name, role, gender, age);
                System.out.println("New staff member added.");
                break;

            case 2:
                System.out.print("Enter Staff ID to Update: ");
                String staffIdToUpdate = sc.nextLine();
                System.out.print("Enter Role: ");
                String newRole = sc.nextLine();

                String newGender = null;
                boolean validNewGender = false;

                while (!validNewGender) {
                    try {
                        System.out.print("Enter Gender (Male/Female): ");
                        newGender = sc.nextLine().toUpperCase();
                        Gender.valueOf(newGender); // Validate input by checking against Gender enum
                        validNewGender = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid gender. Please enter 'Male' or 'Female'.");
                    }
                }

                int newAge = -1;
                boolean validNewAge = false;

                while (!validNewAge) {
                    try {
                        System.out.print("Enter Age: ");
                        newAge = sc.nextInt();
                        sc.nextLine(); // Clear the buffer
                        validNewAge = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid age (integer).");
                        sc.nextLine(); // Clear the invalid input
                    }
                }

                staffManager.updateStaff(staffIdToUpdate, newRole, newGender, newAge);
                System.out.println("Staff member " + staffIdToUpdate + " updated successfully.");
                break;

            case 3:
                System.out.print("Enter Staff ID to Remove: ");
                String staffIdToRemove = sc.nextLine();
                staffManager.removeStaff(staffIdToRemove);
                System.out.println("Staff member removed.");
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

    public void approveRequest(Scanner sc) {
        System.out.print("Enter medication name to approve request: ");
        String medName = sc.nextLine();
        inventoryManager.approveRequest(medName);
    }

    // For admin to view
    public void viewRecord() {
        this.admin.viewRecords();
    }
}
