package controllers;

import java.util.ArrayList;
import java.util.List;

import entities.Patient;
import filehandlers.PatientRepository;

public class PatientManager {
    //MOVE THIS METHOD TO LOGIN MANAGER
    public static void updatePassword(Patient patient, String newPW){
        patient.setPassword(newPW);
    }

    public void viewAllPatients(){
        List<Patient> patientList = PatientRepository.load();
        for (Patient patient : patientList){
            System.out.println(patient.getUserID() + " - " + patient.getName());
        }
    }
    public void viewRecord(String patientID){
        List<Patient> patientList = PatientRepository.load();
        for (Patient patient : patientList){
            if (patient.getUserID().equals(patientID)){
                patient.viewRecords();
            }
        }
    }

    public void updateEmail(String patientID, String newEmail){
        List<Patient> patientList = new ArrayList<>(PatientRepository.load());
        for (Patient patient : patientList){
            if (patient.getUserID().equals(patientID)){
                patient.setEmailAddress(newEmail);
                PatientRepository.store(patientList);
                return;
            }
        }
    }

    public void updatePhoneNumber(String patientID, String newNumber){
        List<Patient> patientList = new ArrayList<>(PatientRepository.load());
        for (Patient patient : patientList){
            if (patient.getUserID().equals(patientID)){
                patient.setPhoneNumber(newNumber);
                PatientRepository.store(patientList);
                return;
            }
        }
    }

    public void addDiagnosis(String patientID, String newDiagnosis){
        List<Patient> patientList = new ArrayList<>(PatientRepository.load());
        for (Patient patient : patientList){
            if (patient.getUserID().equals(patientID)){
                List<String> diagnosisList = new ArrayList<>(patient.getDiagnoses());
                diagnosisList.removeIf(String::isEmpty);
                diagnosisList.add(newDiagnosis);
                patient.setDiagnoses(diagnosisList);
                PatientRepository.store(patientList);
                return;
            }
        }
        
    }

    public void addMedication(String patientID, String newMedication){
        List<Patient> patientList = new ArrayList<>(PatientRepository.load());
        for (Patient patient : patientList){
            if (patient.getUserID().equals(patientID)){
                List<String> medicationList = new ArrayList<>(patient.getPrescribedMedications());
                medicationList.removeIf(String::isEmpty);
                medicationList.add(newMedication);
                patient.setPrescribedMedications(medicationList);
                PatientRepository.store(patientList);
                return;
            }
        }
        
    }

    public void addTreatment(String patientID, String newTreatment){
        List<Patient> patientList = new ArrayList<>(PatientRepository.load());
        for (Patient patient : patientList){
            if (patient.getUserID().equals(patientID)){
                List<String> treatments = new ArrayList<>(patient.getTreatmentPlans());
                treatments.removeIf(String::isEmpty);
                treatments.add(newTreatment);
                patient.setTreatmentPlans(treatments);
                PatientRepository.store(patientList);
                return;
            }
        }
        
        
    }

    public void updateBill(String patientID, double bill){
        List<Patient> patientList = new ArrayList<>(PatientRepository.load());
        for (Patient patient : patientList){
            if (patient.getUserID().equals(patientID)){
                patient.setTotalCost(patient.getTotalcost()+bill);
                PatientRepository.store(patientList);
                return;
            }
        }
        
    }
}
