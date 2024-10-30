package controllers;

import dbinterfaces.StaffRepository;
import entities.Staff;

public class StaffManager {
    //MOVE THIS METHOD TO A CONTROLLER CLASS THAT IS THE PARENT OF STAFF AND PATIENT IN THE FUTURE
    public static void updatePassword(Staff staff, String newPW){
        staff.setPassword(newPW);
        StaffRepository.store(staff);
    }
}