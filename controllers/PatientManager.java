package controllers;

import java.util.ArrayList;
import java.util.List;

import dbinterfaces.PatientRepository;
import entities.Patient;

public class PatientManager extends UserManager{
    private PatientRepository patientRepository;

    //constructor
    public PatientManager(PatientRepository patientRepository) {
        super(patientRepository);
        this.patientRepository = patientRepository;
    }

    public static void viewRecord(Patient patient){
        patient.viewRecords();
    }


    public void updateEmail(Patient patient, String newEmail){
        patient.setEmailAddress(newEmail);
        patientRepository.store(patient);
    }

    //MOVE THIS METHOD TO A CONTROLLER CLASS THAT IS THE PARENT OF STAFF AND PATIENT IN THE FUTURE
    public void updatePassword(Patient patient, String newPW){
        patient.setPassword(newPW);

        patientRepository.store(patient);
    }

    public void updatePhoneNumber(Patient patient, String newNumber){
        patient.setPhoneNumber(newNumber);
        patientRepository.store(patient);
    }

    public void addDiagnosis(Patient patient, String newDiagnosis){
        List<String> diagnosisList = new ArrayList<>(patient.getDiagnoses());
        diagnosisList.removeIf(String::isEmpty);
        diagnosisList.add(newDiagnosis);
        patient.setDiagnoses(diagnosisList);
        patientRepository.store(patient);
    }

    public void addMedication(Patient patient, String newMedication){
        List<String> medicationList = new ArrayList<>(patient.getPrescribedMedications());
        medicationList.removeIf(String::isEmpty);
        medicationList.add(newMedication);
        patient.setPrescribedMedications(medicationList);
        patientRepository.store(patient);
    }

    public void addTreatment(Patient patient, String newTreatment){
        List<String> treatments = new ArrayList<>(patient.getTreatmentPlans());
        treatments.removeIf(String::isEmpty);
        treatments.add(newTreatment);
        patient.setTreatmentPlans(treatments);
        patientRepository.store(patient);
    }
}
