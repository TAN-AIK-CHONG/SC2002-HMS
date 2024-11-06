//NEED TO WRITE CUSTOM EXCEPTIONS

package filehandlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import entities.Admin;
import entities.Doctor;
import entities.Gender;
import entities.Pharmacist;
import entities.Staff;

public class StaffRepository {
    //file path to the staff database
    private static final String STAFF_CSV_FILE = "database\\StaffDatabase.csv";

    //load record from CSV
    public static Staff load(String staffID){
        try (BufferedReader br = new BufferedReader(new FileReader(STAFF_CSV_FILE))){
            String currentLine;

            while((currentLine=br.readLine())!= null){
                String[] data = currentLine.split(",");
                String storedStaffID = data[0];

                if(storedStaffID.equals(staffID)){
                    String name = data[1];
                    String role = data[2];
                    Gender gender = Gender.fromString(data[3]);
                    int age = Integer.parseInt(data[4]);
                    String password = data[5];

                    switch (role.toLowerCase()) {
                        case "doctor":
                            return new Doctor(staffID, name, gender, age, password);
                        case "pharmacist":
                            return new Pharmacist(staffID, name, gender, age, password);
                        case "administrator":
                            return new Admin(staffID, name, gender, age, password);
                        default:
                            return new Staff(staffID, password, name, gender, age, role);  // Default to generic staff
                    }
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    //store record to CSV
    public static void store(Staff updated) {
        File inputFile = new File(STAFF_CSV_FILE);
        File tempFile = new File("temp.csv"); // Temporary file to store updated records

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine=br.readLine())!=null) {
                String[] data = currentLine.split(",");

                // Update the line if it matches the patient ID
                if (data[0].equals(updated.getUserID())) {
                    String updatedLine = String.join(",",
                            updated.getUserID(),
                            updated.getName(),
                            updated.getRole(),
                            updated.getGender().toString(),
                            String.valueOf(updated.getAge()),
                            updated.getPassword());
                    bw.write(updatedLine);
                } else {
                    // Write the original line to the temp file
                    bw.write(currentLine);
                }
                bw.newLine(); 
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating the staff record: " + e.getMessage());
            e.printStackTrace();
        }
        // Replace the original file with the updated file
        if (!inputFile.delete()) {
            System.out.println("Could not delete original file.");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temp file.");
        }
    } 
}
