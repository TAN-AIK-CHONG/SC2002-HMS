package utility;

import entities.Patient;
import entities.Staff;
import filehandlers.PatientRepository;
import filehandlers.StaffRepository;

public class LoginManager {
    public static boolean authenticateUser(String hospitalID, String password, boolean isPatient) {
        if(isPatient){
            Patient patient = PatientRepository.load(hospitalID);
            return patient.authenticate(password);
        }
        Staff staff = StaffRepository.load(hospitalID);
        return staff.authenticate(password);
    }
}