package controllers;

import java.util.List;

import dbinterfaces.InventoryRepository;
import entities.Medication;

public class InventoryManager {
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



}
