//NEED TO WRITE CUSTOM EXCEPTIONS

package filehandlers;

import entities.BloodType;
import entities.Gender;
import entities.Patient;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientRepository {
    // file path to the patient database
    private static final String PATIENT_CSV_FILE = "src\\database\\PatientDatabase.csv";

    // load single patient
    public static Patient load(String patientID) {
        try (BufferedReader br = new BufferedReader(new FileReader(PATIENT_CSV_FILE))) {
            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                String[] data = currentLine.split(",");

                String storedPatientID = data[0];

                // Load csv data into Patient
                if (storedPatientID.equals(patientID)) {
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
                    double totalCost = Double.parseDouble(data[11]);

                    // Create new patient
                    Patient patient = new Patient(patientID, name, dateOfBirth, gender, bloodType,
                            emailAddress, phoneNumber, diagnoses, medications, treatmentPlans, password , totalCost);

                    return patient;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // load list of patients
    public static List<Patient> load() {
        List<Patient> patientList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATIENT_CSV_FILE))) {
            String currentLine;
            br.readLine(); // skip header line

            while ((currentLine = br.readLine()) != null) {
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
                double TotalCost = Double.parseDouble(data[11]);


                // Create new patient
                Patient patient = new Patient(patientID, name, dateOfBirth, gender, bloodType,
                        emailAddress, phoneNumber, diagnoses, medications, treatmentPlans, password , TotalCost);

                patientList.add(patient);
            }

            return patientList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // store list of patients
    public static void store(List<Patient> patientList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATIENT_CSV_FILE))) {
            // Write header row
            bw.write("Patient ID,Name,Date of Birth,Gender,Blood Type,Contact Information,Phone Number,Diagnoses,Medications,Treatment,Password,Total Bill");
            bw.newLine();

            // Write each patient record to the CSV (overwrite)
            for (Patient patient : patientList) {
                String line = patient.getUserID() + "," + patient.getName() + "," + patient.getDateOfBirth() + ","
                        + patient.getGender().toString() + "," + patient.getBloodType().toString() + ","
                        + patient.getEmailAddress() + "," + patient.getPhoneNumber() + ","
                        + String.join(";", patient.getDiagnoses()) + ","
                        + String.join(";", patient.getPrescribedMedications())
                        + "," + String.join(";", patient.getTreatmentPlans()) + "," + patient.getPassword()
                        + "," + patient.getTotalcost();
                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    // we use this method to check if a patient ID exists in the database
    public static boolean doesPatientExist(String patientID) {
        try (BufferedReader br = new BufferedReader(new FileReader(PATIENT_CSV_FILE))) {
            String currentLine;
            br.readLine();

            while ((currentLine = br.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (data[0].equals(patientID)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the patient database: " + e.getMessage());
        }
        return false;
    }
}
