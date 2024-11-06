package controllers;

import java.util.ArrayList;
import java.util.List;

import entities.Patient;
import filehandlers.PatientRepository;

public class PatientManager {
    public void viewAllPatients(){
        List<Patient> patientList = PatientRepository.loadAllPatients();
        for (Patient patient : patientList){
            System.out.println(patient.getUserID() + " - " + patient.getName());
        }

    }
    public void viewRecord(Patient patient){
        patient.viewRecords();
    }

    public void updateEmail(Patient patient, String newEmail){
        patient.setEmailAddress(newEmail);
        PatientRepository.store(patient);
    }

    //MOVE THIS METHOD TO A CONTROLLER CLASS THAT IS THE PARENT OF STAFF AND PATIENT IN THE FUTURE
    public static void updatePassword(Patient patient, String newPW){
        patient.setPassword(newPW);
        PatientRepository.store(patient);
    }

    public void updatePhoneNumber(Patient patient, String newNumber){
        patient.setPhoneNumber(newNumber);
        PatientRepository.store(patient);
    }

    public void addDiagnosis(Patient patient, String newDiagnosis){
        List<String> diagnosisList = new ArrayList<>(patient.getDiagnoses());
        diagnosisList.removeIf(String::isEmpty);
        diagnosisList.add(newDiagnosis);
        patient.setDiagnoses(diagnosisList);
        PatientRepository.store(patient);
    }

    public void addMedication(Patient patient, String newMedication){
        List<String> medicationList = new ArrayList<>(patient.getPrescribedMedications());
        medicationList.removeIf(String::isEmpty);
        medicationList.add(newMedication);
        patient.setPrescribedMedications(medicationList);
        PatientRepository.store(patient);
    }

    public void addTreatment(Patient patient, String newTreatment){
        List<String> treatments = new ArrayList<>(patient.getTreatmentPlans());
        treatments.removeIf(String::isEmpty);
        treatments.add(newTreatment);
        patient.setTreatmentPlans(treatments);
        PatientRepository.store(patient);
    }
}
