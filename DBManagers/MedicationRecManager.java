package DBManagers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import records.MedicationRecord;

public class MedicationRecManager {
    // File path to medication inventory
    private static final String MEDICINE_CSV_FILE = "database/MedicineDatabase.csv";

    // Load medication inventory from the CSV file
    public static List<MedicationRecord> load() {
        List<MedicationRecord> inventory = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(MEDICINE_CSV_FILE))) {
            br.readLine(); //skip the header
            
            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                String[] data = currentLine.split(",");
                String medName = data[0];
                int quantity = Integer.parseInt(data[1]);
                int alertLevel = Integer.parseInt(data[2]);

                MedicationRecord med = new MedicationRecord(medName, quantity,alertLevel);
                inventory.add(med);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inventory;
    }

     // Store medication inventory back to CSV
    public static void store(List<MedicationRecord> inventory) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MEDICINE_CSV_FILE))) {
            // Write header row
            bw.write("Medicine Name,Current Stock,Low Stock Level Alert");
            bw.newLine();

            // Write each medication record to the CSV (overwrite)
            for (MedicationRecord med : inventory) {
                String line = med.getName() + "," + med.getQuantity() + "," + med.getAlertLevel();
                bw.write(line);
                bw.newLine();
            }

            System.out.println("Medication inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
