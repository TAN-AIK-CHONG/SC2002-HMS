package utility;

import dbinterfaces.PatientRepository;
import dbinterfaces.StaffRepository;
import entities.Patient;
import entities.Staff;

public class LoginManager {
    public static boolean authenticateUser(String hospitalID, String password, boolean isPatient) {
        if(isPatient){
            PatientRepository patientRepository = new PatientRepository();
            Patient patient = patientRepository.load(hospitalID);
            return patient.authenticate(password);
        }
        StaffRepository staffRepository = new StaffRepository();
        Staff staff = staffRepository.load(hospitalID);
        return staff.authenticate(password);
    }
}