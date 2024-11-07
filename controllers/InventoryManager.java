package controllers;

import java.util.ArrayList;
import java.util.List;

import entities.Medication;
import filehandlers.InventoryRepository;

public class InventoryManager {
    public void viewInventory(){
        List<Medication> inventory = new ArrayList<>(InventoryRepository.load());
        System.out.println("Medication Inventory:");
        for (Medication med : inventory) {
            med.view();
        }
    }

    public void addInventory(Medication newMed){
        List<Medication> inventory = new ArrayList<>(InventoryRepository.load());
        inventory.add(newMed);
        InventoryRepository.store(inventory);
        System.out.println("Added new medication: " + newMed.getName());
    }

    public void removeInventory(String medName){
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

    public void updateInventory(String medName, int newLevel){
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



}
