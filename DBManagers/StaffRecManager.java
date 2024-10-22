//NEED TO WRITE CUSTOM EXCEPTIONS

package DBManagers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import records.Gender;
import records.StaffRecord;

public class StaffRecManager {
    //file path to the staff database
    private static final String STAFF_CSV_FILE = "database\\StaffDatabase.csv";

    //load record from CSV
    public static StaffRecord load(String staffID){
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

                    StaffRecord staffInfo = new StaffRecord(storedStaffID, name, role, gender, age, password);
                    return staffInfo;
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    //store record to CSV
    public static void store(String staffID, StaffRecord updatedRecord) {
        File inputFile = new File(STAFF_CSV_FILE);
        File tempFile = new File("temp.csv"); // Temporary file to store updated records

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine=br.readLine())!=null) {
                String[] data = currentLine.split(",");

                // Update the line if it matches the patient ID
                if (data[0].equals(staffID)) {
                    String updatedLine = String.join(",",
                            updatedRecord.getStaffID(),
                            updatedRecord.getName(),
                            updatedRecord.getRole(),
                            updatedRecord.getGender().toString(),
                            String.valueOf(updatedRecord.getAge()),
                            updatedRecord.getPassword());
                    bw.write(updatedLine);
                } else {
                    // Write the original line to the temp file
                    bw.write(currentLine);
                }
                bw.newLine(); 
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating the patient record: " + e.getMessage());
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

    //authenticate staff
    public static boolean authenticate(String staffID, String inputPW){
        try (BufferedReader br = new BufferedReader(new FileReader(STAFF_CSV_FILE))){
            String currentLine;

            while((currentLine=br.readLine())!= null){
                String[] data = currentLine.split(",");

                String storedStaffID = data[0];

                //check if staff exists
                if(storedStaffID.equals(staffID)){
                    String password = data[5];
                    if (inputPW.equals(password)){
                        return true;
                    }
                    return false;
                }

            }
        } catch(IOException e){
            e.printStackTrace();
        }
        //staff does not exist
        return false;
    }
}
