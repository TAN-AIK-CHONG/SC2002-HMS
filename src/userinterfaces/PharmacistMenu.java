package userinterfaces;

import controllers.AppointmentManager;
import controllers.InventoryManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PharmacistMenu implements IMenu {
    private String pharmacistID;
    private InventoryManager inventoryManager;
    private AppointmentManager appointmentManager;

    public PharmacistMenu(String pharmacistID, InventoryManager inventoryManager,
        AppointmentManager appointmentManager) {
        this.pharmacistID = pharmacistID;
        this.inventoryManager = inventoryManager;
        this.appointmentManager = appointmentManager;
    }

    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            menuItems();
            try {
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                       viewAppointmentOutcomeRecord(sc);
                       break;
                    case 2:
                        updatePrescriptionStatus(sc);
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
                        System.out.println("Please choose a valid option instead (1-5)");
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
        System.out.println("===================================");
        System.out.println("Pharmacist Menu");
        System.out.println("1. View Appointment Outcome Record");
        System.out.println("2. Update Prescription Status ");
        System.out.println("3. View Medication Inventory");
        System.out.println("4. Submit Replenishment Request");
        System.out.println("5. Logout");
        System.out.println("===================================");
        System.out.println();
        System.out.print("Choose an option: ");
    }

    private void viewAppointmentOutcomeRecord(Scanner sc) {
        System.out.println("Enter Patient ID to view their Appointment Outcome Records: ");
        String patientID = sc.nextLine().toUpperCase();
        appointmentManager.viewAORByPatient(patientID);
    }

    private void submitRequest(Scanner sc) {
        System.out.println("Enter Medicine to be updated");
        String name = sc.nextLine().toUpperCase();
        inventoryManager.submitRequest(name);
    }

    private void updatePrescriptionStatus(Scanner sc) {
        System.out.println("Enter Appointment ID to update status:");
        String apptID = sc.nextLine().toUpperCase();
        System.out.println("Enter updated status of prescription : (PENDING / DISPENSED) : ");
        String status = sc.nextLine().toUpperCase();
        appointmentManager.updatePStatus(apptID, status);
    }

}