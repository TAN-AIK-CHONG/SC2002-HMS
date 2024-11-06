package userinterfaces;

import java.util.List;
import java.util.Scanner;

import dbinterfaces.InventoryRepository;
import controllers.InventoryManager;
import entities.Admin;
import entities.Medication;

public class AdminMenu implements IMenu {
    private Admin admin;
    private InventoryManager inventoryManager;
    
    public AdminMenu(Admin admin, InventoryManager inventoryManager){
        this.admin = admin;
        this.inventoryManager = inventoryManager;
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
                    break;
                case 2:
                    break;
                case 3:
                    List<Medication> inventory = InventoryRepository.load();
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

    //For admin to view
    public void viewRecord(){
        this.admin.viewRecords();
    }
}
