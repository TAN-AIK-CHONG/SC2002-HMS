package userpackage;

import utilitypackage.MedicalRecords;

public class Patient extends User {
    private MedicalRecords medicalRecords;

    public Patient(String patientID, String password, MedicalRecords patientRecords) {
        super(patientID, password, "Patient");
        this.medicalRecords = patientRecords;
    }

    @Override
    public void displayMenu() {
        System.out.println("Patient Menu:");
        System.out.println("1. View Medical Record");
        System.out.println("2. Update Personal Information");
        System.out.println("3. View Available Appointment Slots");
        System.out.println("4. Schedule an Appointment");
        System.out.println("5. Reschedule an Appointment");
        System.out.println("6. Cancel an Appointment");
        System.out.println("7. View Scheduled Appointments");
        System.out.println("8. View Past Appointment Outcome Records");
        System.out.println("9. Logout");
    }

    public void viewMedicalRecords(){
        medicalRecords.viewRecords();
    }

    public void updateInfo(String newAddress, String newNumber){
        medicalRecords.setEmailAdd(newAddress);
        medicalRecords.setPhoneNum(newNumber);
    }


}
