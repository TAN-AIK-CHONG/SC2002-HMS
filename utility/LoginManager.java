package utility;

import DBManagers.PatientRecManager;
import DBManagers.StaffRecManager;

public class LoginManager {
    public static boolean authenticateUser(String hospitalID, String password, boolean isPatient) {
        if(isPatient){
            return PatientRecManager.authenticate(hospitalID,password);
        }
        return StaffRecManager.authenticate(hospitalID,password);
    }
}