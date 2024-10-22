//NEED TO WRITE CUSTOM EXCEPTIONS

package DBManagers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import records.BloodType;
import records.Gender;
import records.PatientRecord;

public class PatientRecManager{
    //file path to the patient database
    private static final String PATIENT_CSV_FILE = "database\\PatientDatabase.csv";

    //load record from CSV
    public static PatientRecord load(String patientID){
        try (BufferedReader br = new BufferedReader(new FileReader(PATIENT_CSV_FILE))){
            String currentLine;

            while((currentLine=br.readLine())!= null){
                String[] data = currentLine.split(",");

                String storedPatientID = data[0];

                //Load csv data into new MedicalRecord once we locate the patient
                if(storedPatientID.equals(patientID)){
                    String name = data[1];
                    String dateOfBirth = data[2];
                    Gender gender = Gender.fromString(data[3]);
                    BloodType bloodType = BloodType.fromString(data[4]);
                    String emailAddress = data[5];
                    String phoneNumber = data[6];
                    List<String> diagnoses = Arrays.asList(data[7].split(";"));
                    List<String> medications = Arrays.asList(data[8].split(";"));
                    List<String> treatmentPlans = Arrays.asList(data[9].split(";"));
                    String password = data[10];

                    //Create new MedicalRecord for this patient
                    PatientRecord record = new PatientRecord(storedPatientID, name, dateOfBirth, gender, 
                                    emailAddress, phoneNumber, bloodType, diagnoses, medications, treatmentPlans, password);

                    return record;

                }

            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    //store record to CSV
    public static void store(String patientID, PatientRecord updatedRecord) {
        File inputFile = new File(PATIENT_CSV_FILE);
        File tempFile = new File("temp.csv"); // Temporary file to store updated records

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine=br.readLine())!=null) {
                String[] data = currentLine.split(",");

                // Update the line if it matches the patient ID
                if (data[0].equals(patientID)) {
                    String updatedLine = String.join(",",
                            updatedRecord.getPatientID(),
                            updatedRecord.getName(),
                            updatedRecord.getDOB(),
                            updatedRecord.getGender().toString(),
                            updatedRecord.getBloodType().toString(),
                            updatedRecord.getEmailAdd(),
                            updatedRecord.getPhoneNum(),
                            String.join(";", updatedRecord.getDiagnoses()),
                            String.join(";", updatedRecord.getPrescribedMedications()),
                            String.join(";", updatedRecord.getTreatmentPlans()),
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

    //authenticate user
    public static boolean authenticate(String patientID, String inputPW){
        try (BufferedReader br = new BufferedReader(new FileReader(PATIENT_CSV_FILE))){
            String currentLine;

            while((currentLine=br.readLine())!= null){
                String[] data = currentLine.split(",");

                String storedPatientID = data[0];

                //check if patient exists
                if(storedPatientID.equals(patientID)){
                    String password = data[10];
                    if (inputPW.equals(password)){
                        return true;
                    }
                    return false;
                }

            }
        } catch(IOException e){
            e.printStackTrace();
        }
        //patient does not exist
        return false;
    }
}
