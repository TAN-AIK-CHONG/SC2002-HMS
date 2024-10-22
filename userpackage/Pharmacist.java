package userPackage;

import java.util.List;
import java.util.Scanner;

import DBManagers.MedicationRecManager;
import records.MedicationRecord;
import records.StaffRecord;

public class Pharmacist implements IMenu {
    private StaffRecord record;

    public Pharmacist(StaffRecord record){
        this.record = record;
    }

    private void viewInventory(){
        List<MedicationRecord> inventory = MedicationRecManager.load();
        System.out.println("Medication Inventory:");
        for (MedicationRecord med : inventory) {
            med.view();
        }
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
                    viewInventory();
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

    //for admin to view
    public void viewRecord(){
        this.record.view();
    }
}
