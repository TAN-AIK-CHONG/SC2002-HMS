//NEED TO WRITE CUSTOM EXCEPTIONS

package dbinterfaces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.BloodType;
import entities.Gender;
import entities.Patient;

public class PatientRepository{
    //file path to the patient database
    private static final String PATIENT_CSV_FILE = "database\\PatientDatabase.csv";

    //load record from CSV
    public static Patient load(String patientID){
        try (BufferedReader br = new BufferedReader(new FileReader(PATIENT_CSV_FILE))){
            String currentLine;

            while((currentLine=br.readLine())!= null){
                String[] data = currentLine.split(",");

                String storedPatientID = data[0];

                //Load csv data into Patient
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

                    //Create new patient
                    Patient patient = new Patient(patientID, name, dateOfBirth, gender, bloodType,
                                                    emailAddress, phoneNumber, diagnoses, medications, treatmentPlans, password);

                    return patient;

                }

            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    //store record to CSV
    public static void store(Patient updated) {
        File inputFile = new File(PATIENT_CSV_FILE);
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
                            updated.getDateOfBirth(),
                            updated.getGender().toString(),
                            updated.getBloodType().toString(),
                            updated.getEmailAddress(),
                            updated.getPhoneNumber(),
                            String.join(";", updated.getDiagnoses()),
                            String.join(";", updated.getPrescribedMedications()),
                            String.join(";", updated.getTreatmentPlans()),
                            updated.getPassword());
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

    public static List<Patient> loadAllPatients(){
        List<Patient> patientList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATIENT_CSV_FILE))){
            String currentLine;
            br.readLine(); //skip header line

            while((currentLine=br.readLine())!= null){
                String[] data = currentLine.split(",");

                String patientID = data[0];
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

                //Create new patient
                Patient patient = new Patient(patientID, name, dateOfBirth, gender, bloodType,
                                                    emailAddress, phoneNumber, diagnoses, medications, treatmentPlans, password);

                patientList.add(patient);
                }

            return patientList;
            } catch(IOException e){
            e.printStackTrace();
            }
        return null;
    }
}
