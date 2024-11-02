package userinterfaces;

import java.util.List;
import java.util.Scanner;

import controllers.InventoryManager;
import controllers.AppointmentManager;
import dbinterfaces.InventoryRepository;
import entities.Appointment;
import entities.Medication;
import entities.Prescription;
import entities.Staff;
import controllers.AppointmentManager;
import controllers.InventoryManager;

public class PharmacistMenu implements IMenu {
    private Staff record;

    public PharmacistMenu(Staff record){
        this.record = record;
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
                    viewAppointmentOutcomes();
                    break;
                case 2:
                    updatePrescriptionStatus(sc);
                    break;
                case 3:
                    List<Medication> inventory = InventoryRepository.load();
                    InventoryManager inventoryManager=new InventoryManager();
                    inventoryManager.viewInventory(inventory);
                    break;
                case 4:
                    submitReplenishmentRequest(sc);
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
    private void viewAppointmentOutcomes() {
        List<Appointment> completedAppointments = appointmentManager.getCompletedAppointmentsWithPrescriptions();
        System.out.println("\nCompleted Appointments for Prescription Fulfillment:");
        for (Appointment appointment : completedAppointments) {
            System.out.println("Appointment ID: " + appointment.getAppointmentId());
            System.out.println("Patient ID: " + appointment.getPatientId());
            System.out.println("Doctor ID: " + appointment.getDoctorId());
            System.out.println("Date: " + appointment.getDate() + " Time: " + appointment.getTime());
            System.out.println("Prescriptions:");
            for (Prescription prescription : appointment.getPrescriptions()) {
                System.out.println(" - " + prescription.getMedicationName() + ": " + prescription.getStatus());
            }
            System.out.println();
        }
    }

    private void updatePrescriptionStatus(Scanner sc) {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Medication Name to Update Status: ");
        String medicationName = sc.nextLine();

        boolean updated = appointmentManager.updatePrescriptionStatus(appointmentId, medicationName, "dispensed");
        if (updated) {
            System.out.println("Prescription status updated to 'dispensed'.");
        } else {
            System.out.println("Prescription status update failed. Please check the appointment ID and medication name.");
        }
    }

    private void viewInventory() {
        List<Medication> inventory = InventoryRepository.load();
        inventoryManager.viewInventory(inventory);
    }

    private void submitReplenishmentRequest(Scanner sc) {
        System.out.print("Enter Medication Name for Replenishment: ");
        String medicationName = sc.nextLine();
        System.out.print("Enter Quantity Needed: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        boolean requestSubmitted = inventoryManager.submitReplenishmentRequest(medicationName, quantity);
        if (requestSubmitted) {
            System.out.println("Replenishment request submitted successfully.");
        } else {
            System.out.println("Failed to submit replenishment request. Please check the medication name.");
        }
    }
    //for admin to view
    public void viewRecord(){
        this.record.viewRecords();
    }
}
