package controllers;

import java.util.List;
import java.util.ArrayList;

import entities.Gender;
import entities.Staff;
import filehandlers.StaffRepository;
import utility.Hash;

public class StaffManager {
    //MOVE THIS METHOD TO A CONTROLLER CLASS THAT IS THE PARENT OF STAFF AND PATIENT IN THE FUTURE
    public static void updatePassword(Staff staff, String newPW){
        staff.setPassword(newPW);
    }

    public void viewAllStaff(){
        List<Staff> staffList = StaffRepository.loadAllStaff();
        for (Staff staff : staffList){
            System.out.println(staff.getUserID() + " - " + staff.getName());
        }
    }

    public void addStaff(String staffID, String password, String name, String role, Gender gender, int age){
        List<Staff> staffList = new ArrayList<>(StaffRepository.loadAllStaff());
        String HashedPW = Hash.hashWith256(password);
        Staff newStaff = new Staff(staffID, HashedPW, name, gender, age, role);
        staffList.add(newStaff);
        StaffRepository.store(staffList);
    }

    public void updateStaff(String staffID, String newRole, String newGender, int newAge){
        List<Staff> staffList = new ArrayList<>(StaffRepository.loadAllStaff());
        for (Staff staff : staffList){
            if (staff.getUserID().equals(staffID)){
                staff.setRole(newRole);
                staff.setGender(Gender.fromString(newGender));
                staff.setAge(newAge);
                StaffRepository.store(staffList);
                return;
            }
        }
        System.out.println("No such staff member exists");
    }

    public void removeStaff(String staffID){
        List<Staff> staffList = new ArrayList<>(StaffRepository.loadAllStaff());
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getUserID().equals(staffID)) {
                staffList.remove(i);
                StaffRepository.store(staffList);
                return;
            }
        }
        System.out.println("No such staff member exists");
    }
}
