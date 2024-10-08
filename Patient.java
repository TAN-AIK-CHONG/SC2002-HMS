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
        System.out.println("The following are your records:");
        System.out.println("Patient ID: " + medicalRecords.getPatientID());
        System.out.println("Name: " + medicalRecords.getPatientName());
        System.out.println("Date of Birth " + medicalRecords.getDOB());
        System.out.println("Gender: " + medicalRecords.getGender());
        System.out.println("Email Address: " + medicalRecords.getEmailAdd());
        System.out.println("Phone Number: " + medicalRecords.getPhoneNum());
        System.out.println("Blood Type: " + medicalRecords.getBloodType());
        System.out.println("Past Diagnoses: " + medicalRecords.getDiagnoses());
        System.out.println("Treatments: " + medicalRecords.getTreatmentPlans());
    }

    public void updateInfo(String newAddress, String newNumber){
        medicalRecords.setEmailAdd(newAddress);
        medicalRecords.setPhoneNum(newNumber);
    }


}
