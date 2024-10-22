package userPackage;

import java.util.List;
import java.util.Scanner;

import DBManagers.MedicationRecManager;
import records.MedicationRecord;
import records.StaffRecord;

public class Admin implements IMenu {
    private StaffRecord record;
    
    public Admin(StaffRecord record){
        this.record = record;
    }

    private void viewInventory(List<MedicationRecord> inventory){
        System.out.println("Medication Inventory:");
        for (MedicationRecord med : inventory) {
            med.view();
        }
    }

    private void addInventory(List<MedicationRecord> inventory, String medName, int qty, int alert){
        MedicationRecord newMed = new MedicationRecord(medName, qty, alert);
        inventory.add(newMed);
        System.out.println("Added new medication: " + medName);
    }

    private void removeInventory(List<MedicationRecord> inventory, String medName){
        for (int i = 0; i < inventory.size(); i++) {
            MedicationRecord med = inventory.get(i);
            if (med.getName().equalsIgnoreCase(medName)) {
                inventory.remove(i); 
                System.out.println("Removed medication: " + medName);
                return;
            }
        }
        System.out.println("No such medication found");
    }

    private void updateInventory(List<MedicationRecord> inventory, String medName, int newLevel){
        for (int i = 0; i < inventory.size(); i++) {
            MedicationRecord med = inventory.get(i);
            if (med.getName().equalsIgnoreCase(medName)) {
                med.setQuantity(newLevel);
                return;
            }
        }
        System.out.println("No such medication found");
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
                    List<MedicationRecord> inventory = MedicationRecManager.load();
                    viewInventory(inventory);
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
                            String newMed = sc.nextLine();
                            System.out.print("Enter initial stock quantity: ");
                            int quantity = sc.nextInt();
                            sc.nextLine(); 
                            System.out.print("Enter low stock alert level:");
                            int alert = sc.nextInt();
                            sc.nextLine();
                            addInventory(inventory, newMed, quantity, alert);
                            break;
                        case 2:
                            System.out.print("Enter medication to be removed: ");
                            String removedMed = sc.nextLine();
                            removeInventory(inventory, removedMed);
                            break;
                        case 3:
                            System.out.print("Enter medication to be updated:");
                            String updatedMed = sc.nextLine();
                            System.out.print("Enter new stock level: ");
                            int newLevel = sc.nextInt();
                            sc.nextLine();
                            updateInventory(inventory, updatedMed, newLevel);
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Please choose a valid option (1-3)");
                            break;
                    }
                    MedicationRecManager.store(inventory);
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
        this.record.view();
    }
}
