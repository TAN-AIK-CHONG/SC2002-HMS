package controllers;

import java.util.ArrayList;
import java.util.List;

import dbinterfaces.InventoryRepository;
import entities.Medication;

public class InventoryManager {
    private List<Medication> replenishmentRequests = new ArrayList<>();
    public void viewInventory(List<Medication> inventory){
        System.out.println("Medication Inventory:");
        for (Medication med : inventory) {
            med.view();
        }
    }

    public void addInventory(List<Medication> inventory, Medication newMed){
        inventory.add(newMed);
        InventoryRepository.store(inventory);
        System.out.println("Added new medication: " + newMed.getName());
    }

    public void removeInventory(List<Medication> inventory, String medName){
        for (int i = 0; i < inventory.size(); i++) {
            Medication med = inventory.get(i);
            if (med.getName().equalsIgnoreCase(medName)) {
                inventory.remove(i); 
                InventoryRepository.store(inventory);
                System.out.println("Removed medication: " + medName);
                return;
            }
        }
        System.out.println("No such medication found");
    }

    public void updateInventory(List<Medication> inventory, String medName, int newLevel){
        for (int i = 0; i < inventory.size(); i++) {
            Medication med = inventory.get(i);
            if (med.getName().equalsIgnoreCase(medName)) {
                med.setQuantity(newLevel);
                InventoryRepository.store(inventory);
                return;
            }
        }
        System.out.println("No such medication found");
    }
    // Submit a replenishment request
    public boolean submitReplenishmentRequest(String medicationName, int quantity) {
        Medication request = new Medication(medicationName, 0, 0); // Placeholder for alert level
        request.setReplenishmentQuantity(quantity);
        replenishmentRequests.add(request);
        System.out.println("Replenishment request for " + quantity + " units of " + medicationName + " submitted.");
        return true;
    }

    // Retrieve pending replenishment requests
    public List<Medication> getReplenishmentRequests() {
        return replenishmentRequests;
    }

    // Approve all pending replenishment requests
    public void approveReplenishmentRequests() {
        for (Medication request : replenishmentRequests) {
            System.out.println("Replenishment approved for " + request.getName() + " (" + request.getReplenishmentQuantity() + " units)");
            // Logic to update inventory with replenishment quantities could be added here
        }
        replenishmentRequests.clear(); // Clear the list of requests after approval
    }
}
