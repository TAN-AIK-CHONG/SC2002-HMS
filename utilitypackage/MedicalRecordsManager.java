package utilitypackage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import userpackage.BloodType;
import userpackage.Gender;

public class MedicalRecordsManager {

    //load records from csv 
    public static MedicalRecords loadRecords(String patientID){
        String filePath = "database\\PatientList.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
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
                    MedicalRecords patientRecords = new MedicalRecords(storedPatientID, name, dateOfBirth, gender, 
                                    emailAddress, phoneNumber, bloodType, diagnoses, medications, treatmentPlans, password);

                    return patientRecords;

                }

            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}