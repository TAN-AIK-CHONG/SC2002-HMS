package controllers;

import entities.Medication;
import filehandlers.InventoryRepository;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    public void viewInventory() {
        List<Medication> inventory = new ArrayList<>(InventoryRepository.load());
        System.out.println("Medication Inventory:");
        for (Medication med : inventory) {
            med.view();
        }
    }

    public void addInventory(Medication newMed) {
        List<Medication> inventory = new ArrayList<>(InventoryRepository.load());
        inventory.add(newMed);
        InventoryRepository.store(inventory);
        System.out.println("Added new medication: " + newMed.getName());
    }

    public void removeInventory(String medName) {
        List<Medication> inventory = new ArrayList<>(InventoryRepository.load());
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

    public void updateInventory(String medName, int newLevel) {
        List<Medication> inventory = new ArrayList<>(InventoryRepository.load());
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

    public void dispenseMedicine(List<String> medicines) {
        List<Medication> inventory = new ArrayList<>(InventoryRepository.load());
        boolean inventoryUpdated = false; 
    
        for (String medicineName : medicines) {
            boolean found = false; 
    
            for (Medication med : inventory) {
                if (med.getName().equalsIgnoreCase(medicineName)) {
                    found = true;
    
                    // Check if the stock level is sufficient
                    if (med.getQuantity() > 0) {
                        med.setQuantity(med.getQuantity() - 1);
                        System.out.println("Dispensed one unit of " + med.getName() + ". New quantity: " + med.getQuantity());
                        inventoryUpdated = true; 
                    } else {
                        // Stock level is zero; cannot dispense
                        System.out.println("Cannot dispense " + med.getName() + ": Out of stock.");
                    }
                    break;
                }
            }
    
            if (!found) {
                // Medicine not found in the inventory
                System.out.println("Medication " + medicineName + " not found in inventory.");
            }
        }
    
        // Save the updated inventory back to the repository if there were any changes
        if (inventoryUpdated) {
            InventoryRepository.store(inventory);
        }
    }
    

    public void submitRequest(String medName) {
        List<Medication> inventory = new ArrayList<>(InventoryRepository.load());
        for (int i = 0; i < inventory.size(); i++) {
            Medication med = inventory.get(i);
            if (med.getName().equalsIgnoreCase(medName)) {
                med.setRequest(true);
                InventoryRepository.store(inventory);
                return;
            }
        }
        System.out.println("No such medication found");
    }

    public void viewRequests(){
        List<Medication> inventory = InventoryRepository.load();
        for (int i = 0; i < inventory.size(); i++) {
            Medication med = inventory.get(i);
            if (med.getRequest()){
                med.view();
            }
        }
    }

    public void approveRequest(String medName) {
        List<Medication> inventory = new ArrayList<>(InventoryRepository.load());
        for (int i = 0; i < inventory.size(); i++) {
            Medication med = inventory.get(i);
            if (med.getName().equalsIgnoreCase(medName)) {
                med.setRequest(false);
                med.setQuantity(med.getOriginal());
                InventoryRepository.store(inventory);
                return;
            }
        }
        System.out.println("No such medication found");
    }

    public boolean doesMedicationExist(String medicationName) {
        List<Medication> inventory = InventoryRepository.load();
        for (Medication med : inventory) {
            if (med.getName().equalsIgnoreCase(medicationName)) {
                return true;
            }
        }
        return false;
    }
}
