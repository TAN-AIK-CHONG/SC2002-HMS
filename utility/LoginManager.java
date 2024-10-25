package utility;

import dbinterfaces.PatientRepository;
import dbinterfaces.StaffRepository;
import entities.Patient;
import entities.Staff;

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