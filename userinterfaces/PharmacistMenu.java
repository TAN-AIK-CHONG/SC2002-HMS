package userinterfaces;

import controllers.InventoryManager;
import entities.Staff;
import java.util.Scanner;
import java.util.InputMismatchException;

public class PharmacistMenu implements IMenu {
    private Staff record;
    private InventoryManager inventoryManager;

    public PharmacistMenu(Staff record, InventoryManager inventoryManager) {
        this.record = record;
        this.inventoryManager = inventoryManager;
    }

    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while (true) {
            System.out.println("===================================");
            System.out.println("Pharmacist Menu");
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Logout");
            System.out.println("===================================");
            System.out.println();

            try {
                System.out.print("Choose an option: ");
                choice = sc.nextInt();
                sc.nextLine(); // Clear the buffer

                switch (choice) {
                    case 1:
                        viewRecord();
                        break;
                    case 2:
                        break;
                    case 3:
                        inventoryManager.viewInventory();
                        break;
                    case 4:
                        submitRequest(sc);
                        break;
                    case 5:
                        sc.close();
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Please choose a valid option (1-5).");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                sc.nextLine(); // Clear the invalid input from scanner buffer
            }
        }
    }

    // for admin to view
    public void viewRecord() {
        this.record.viewRecords();
    }

    private void submitRequest(Scanner sc) {
        System.out.println("Enter Medicine to be updated");
        String name = sc.nextLine();
        inventoryManager.submitRequest(name);
    }
}
