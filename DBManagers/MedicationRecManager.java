package DBManagers;

import java.io.BufferedReader;
import java.io.FileReader;
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
}
