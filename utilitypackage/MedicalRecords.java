package utilitypackage;

import java.util.List;

import userpackage.BloodType;
import userpackage.Gender;

public class MedicalRecords {
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

    public MedicalRecords(String patientID, String name, String dateOfBirth, Gender gender, 
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
        this.diagnoses = pastDiagnoses;
        this.prescribedMedications = prescribedMedications;
        this.treatmentPlans = treatmentPlans;
        this.password = password;
    }


    //getters
    public String getPatientID(){
        return this.patientID;
    }

    public String getPatientName(){
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
        this.diagnoses.add(newDiagnosis); //for doctor
    }

    public void addPrescription(String newPrescription){
        this.prescribedMedications.add(newPrescription); //for doctor
    }

    public void addTreatmentPlan(String newTreatment){
        this.treatmentPlans.add(newTreatment); //for doctor
    }

    public void setNewPassword(String newPassword){
        this.password = newPassword; //for patient
    }
}
