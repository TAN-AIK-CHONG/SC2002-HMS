package userpackage;

import utilitypackage.MedicalRecords;
import utilitypackage.MedicalRecordsManager;

public class Doctor extends User {

    public Doctor(String doctorID, String password){
        super(doctorID, password, "Doctor");
    }

    @Override
    public void displayMenu(){
        System.out.println("not implemented yet");
    }

    public void viewPatientRecords(String patientID){
        MedicalRecords patientRecord = MedicalRecordsManager.loadRecords(patientID);
        patientRecord.viewRecords();
    }

    public void addDiagnosis(String patientID, String newDiagnosis){
        MedicalRecords patientRecord = MedicalRecordsManager.loadRecords(patientID);
        patientRecord.addDiagnosis(newDiagnosis);
    }

    public void addPrescription(String patientID, String newPrescription){
        MedicalRecords patientRecord = MedicalRecordsManager.loadRecords(patientID);
        patientRecord.addPrescription(newPrescription);
    }
}