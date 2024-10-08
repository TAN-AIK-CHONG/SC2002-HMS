public class LoginManager {
    public static boolean authenticateUser(String hospitalID, String password, boolean isPatient) {
        if(isPatient){
            MedicalRecords newRecord = MedicalRecordsManager.loadRecords(hospitalID);
            if (newRecord != null && newRecord.getPatientID().equals(hospitalID) && newRecord.getPassword().equals(password)){
                return true;
            }
            return false;
        }
        //implement staff records later
        return false;
    }
}
