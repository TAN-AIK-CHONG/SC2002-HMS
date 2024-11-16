package userinterfaces;

import controllers.AppointmentManager;
import controllers.InventoryManager;
import entities.Staff;
import entities.appointments.AOR;
import entities.appointments.ApptPrescription;
import java.util.Scanner;

public class PharmacistMenu implements IMenu {
    private Staff record;
    private InventoryManager inventoryManager;
    private AppointmentManager appointmentManager;

    public PharmacistMenu(Staff record, InventoryManager inventoryManager, AppointmentManager appointmentManager) {
        this.record = record;
        this.inventoryManager = inventoryManager;
        this.appointmentManager = appointmentManager;
    }

    public void displayMenu() {
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

        while (true) {
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
                    System.out.println("Please choose a valid option instead");
                    break;
            }
            System.out.print("Choose another option: ");
            choice = sc.nextInt();
            sc.nextLine();
        }
    }

    // for admin to view
    private void viewAppointmentOutcomeRecord(Scanner sc) {
        System.out.println("Enter appointment ID to view Appointment Outcome Record");
        String apptID = sc.nextLine().toUpperCase();
        appointmentManager.viewAOR(apptID);
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