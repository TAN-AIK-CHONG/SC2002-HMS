package controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

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
        List<Staff> staffList = StaffRepository.load();
        for (Staff staff : staffList){
            System.out.println(staff.getUserID() + " - " + staff.getName());
        }
    }

    public void addStaff(String staffID, String password, String name, String role, Gender gender, int age){
        List<Staff> staffList = new ArrayList<>(StaffRepository.load());
        String HashedPW = Hash.hashWith256(password);
        Staff newStaff = new Staff(staffID, HashedPW, name, gender, age, role);
        staffList.add(newStaff);
        StaffRepository.store(staffList);
    }

    public void updateStaff(String staffID, String newRole, String newGender, int newAge){
        List<Staff> staffList = new ArrayList<>(StaffRepository.load());
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
        List<Staff> staffList = new ArrayList<>(StaffRepository.load());
        Iterator<Staff> iterator = staffList.iterator();
        while (iterator.hasNext()) {
            Staff staff = iterator.next();
            if (staff.getUserID().equals(staffID)) {
                iterator.remove();
                StaffRepository.store(staffList);
                return;
            }
        }
}
