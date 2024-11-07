package userinterfaces;

import controllers.InventoryManager;
import entities.Staff;
import java.util.Scanner;

public class PharmacistMenu implements IMenu {
    private Staff record;
    private InventoryManager inventoryManager;

    public PharmacistMenu(Staff record, InventoryManager inventoryManager){
        this.record = record;
        this.inventoryManager = inventoryManager;
    }

    public void displayMenu(){
        System.out.println("Pharmacist Menu");
        System.out.println("1. View Appointment Outcome Record");
        System.out.println("2. Update Prescription Status ");
        System.out.println("3. View Medication Inventory");
        System.out.println("4. Submit Replenishment Request");
        System.out.println("5. Logout");
        System.out.print("Choose an option: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();

        while(true){
            switch (choice) {
                case 1:
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
                    System.out.println("Please choose a valid option instead");
                    break;
            }
            System.out.print("Choose another option: ");
            choice = sc.nextInt();
            sc.nextLine();
        }
    }

    //for admin to view
    public void viewRecord(){
        this.record.viewRecords();
    }
    

    private void submitRequest(Scanner sc){
        System.out.println("Enter Medicine to be updated");
        String name = sc.nextLine();
        inventoryManager.submitRequest(name);
    }
}
