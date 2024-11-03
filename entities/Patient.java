package entities;
import controllers.AppointmentManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Patient extends User{
    private String dateOfBirth;
    private String emailAddress;
    private String phoneNumber;
    private BloodType bloodType;
    private List<String> diagnoses;
    private List<String> prescribedMedications;
    private List<String> treatmentPlans;
    private String patientId;
    public Patient(String patientID, String name, String dateOfBirth, Gender gender, 
                          BloodType bloodType, String emailAddress, String phoneNumber,  
                          List<String> pastDiagnoses, List<String> prescribedMedications, 
                          List<String> treatmentPlans, String password) {
        super(patientID, password, name, gender);
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.bloodType = bloodType;
        this.diagnoses = pastDiagnoses;
        this.prescribedMedications = prescribedMedications;
        this.treatmentPlans = treatmentPlans;
    }

    public void viewRecords(){
        System.out.println("Patient ID: " + super.getUserID());
        System.out.println("Name: " + super.getName());
        System.out.println("Date of Birth " + this.dateOfBirth);
        System.out.println("Gender: " + super.getGender().toString());
        System.out.println("Email Address: " + this.emailAddress);
        System.out.println("Phone Number: " + this.phoneNumber);
        System.out.println("Blood Type: " + this.bloodType);
        System.out.println("Past Diagnoses: " + this.diagnoses);
        System.out.println("Prescribed Medications: " + this.prescribedMedications);
        System.out.println("Treatments: " + this.treatmentPlans);
    }

    //getters and setters
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public List<String> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<String> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public List<String> getPrescribedMedications() {
        return prescribedMedications;
    }

    public void setPrescribedMedications(List<String> prescribedMedications) {
        this.prescribedMedications = prescribedMedications;
    }

    public List<String> getTreatmentPlans() {
        return treatmentPlans;
    }

    public void setTreatmentPlans(List<String> treatmentPlans) {
        this.treatmentPlans = treatmentPlans;
    }

    
}