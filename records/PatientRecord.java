//NEED TO INCLUDE GETTERS AND SETTERS FOR ALL

package records;

import java.util.ArrayList;
import java.util.List;

public class PatientRecord implements IRecord{
    private String patientID;
    private String name;
    private String dateOfBirth;
    private Gender gender;
    private String emailAddress;
    private String phoneNumber;
    private BloodType bloodType;
    private List<String> diagnoses;
    private List<String> prescribedMedications;
    private List<String> treatmentPlans;
    private String password;

    public PatientRecord(String patientID, String name, String dateOfBirth, Gender gender, 
                          String emailAddress, String phoneNumber, BloodType bloodType, 
                          List<String> pastDiagnoses, List<String> prescribedMedications, 
                          List<String> treatmentPlans, String password) {
        this.patientID = patientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.bloodType = bloodType;
        this.diagnoses = new ArrayList<>(pastDiagnoses);
        this.prescribedMedications = new ArrayList<>(prescribedMedications);
        this.treatmentPlans = new ArrayList<>(treatmentPlans);
        this.password = password;
    }

    public void view(){
        System.out.println("Patient ID: " + this.patientID);
        System.out.println("Name: " + this.name);
        System.out.println("Date of Birth " + this.dateOfBirth);
        System.out.println("Gender: " + this.gender);
        System.out.println("Email Address: " + this.emailAddress);
        System.out.println("Phone Number: " + this.phoneNumber);
        System.out.println("Blood Type: " + this.bloodType);
        System.out.println("Past Diagnoses: " + this.diagnoses);
        System.out.println("Prescribed Medications: " + this.prescribedMedications);
        System.out.println("Treatments: " + this.treatmentPlans);
    }

    //getters
    public String getPatientID(){
        return this.patientID;
    }

    public String getName(){
        return this.name;
    }

    public String getDOB(){
        return this.dateOfBirth;
    }

    public Gender getGender(){
        return this.gender;
    }

    public String getEmailAdd(){
        return this.emailAddress;
    }

    public String getPhoneNum(){
        return this.phoneNumber;
    }

    public BloodType getBloodType(){
        return this.bloodType;
    }

    public List<String> getDiagnoses(){
        return this.diagnoses;
    }

    public List<String> getPrescribedMedications(){
        return this.prescribedMedications;
    }

    public List<String> getTreatmentPlans(){
        return this.treatmentPlans;
    }

    public String getPassword(){
        return this.password;
    }

    //setters    
    public void setEmailAdd(String newAddress){
        this.emailAddress = newAddress; //for patient
    }

    public void setPhoneNum(String newNumber){
        this.phoneNumber = newNumber; //for patient
    }

    public void addDiagnosis(String newDiagnosis){
        this.diagnoses.removeIf(String::isEmpty);
        this.diagnoses.add(newDiagnosis); //for doctor
    }

    public void addPrescription(String newPrescription){
        this.prescribedMedications.removeIf(String::isEmpty);
        this.prescribedMedications.add(newPrescription); //for doctor
    }

    public void addTreatmentPlan(String newTreatment){
        this.treatmentPlans.removeIf(String::isEmpty);
        this.treatmentPlans.add(newTreatment); //for doctor
    }

    public void setNewPassword(String newPassword){
        this.password = newPassword; //for patient
    }
}
