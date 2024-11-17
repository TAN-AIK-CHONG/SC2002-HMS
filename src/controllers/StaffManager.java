package controllers;

import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import entities.Gender;
import entities.Staff;
import filehandlers.StaffRepository;

public class StaffManager {

    public void updatePassword(Staff staff, String newPW) {
        List<Staff> staffList = StaffRepository.load();
        for (Staff currentStaff : staffList){
            if (currentStaff.getUserID().equals(staff.getUserID())){
                currentStaff.setPassword(newPW);
                StaffRepository.store(staffList);
                return;
            }
        }
    }

    public void viewAllStaffDetails() {
        List<Staff> staffList = StaffRepository.load();
        for (Staff staff : staffList) {
            staff.viewRecords();
            System.out.println();
        }
    }

    public void viewStaffList() {
        List<Staff> staffList = StaffRepository.load();
        for (Staff staff : staffList) {
            System.out.println(staff.getUserID() + " - " + staff.getName());
        }
    }

    public void addStaff(String staffID, String password, String name, String role, Gender gender, int age) {
        List<Staff> staffList = new ArrayList<>(StaffRepository.load());
        for (Staff staff : staffList){
            if (staff.getUserID().equals(staffID)){
                System.out.println("StaffID taken! Please choose another ID.");
                return;
            }
        }
        String HashedPW = BCrypt.hashpw(password, BCrypt.gensalt());
        Staff newStaff = new Staff(staffID, HashedPW, name, gender, age, role);
        staffList.add(newStaff);
        StaffRepository.store(staffList);
        System.out.println("Staff successfully added.");
    }

    public void updateStaff(String staffID, String newRole, Gender gender, int newAge) {
        List<Staff> staffList = new ArrayList<>(StaffRepository.load());
        for (Staff staff : staffList) {
            if (staff.getUserID().equals(staffID)) {
                staff.setRole(newRole);
                staff.setGender(gender);
                staff.setAge(newAge);
                StaffRepository.store(staffList);
                System.out.println("Staff member " +staff.getUserID()+ " updated succesfully");
                return;
            }
        }
        System.out.println("No such staff member exists");
    }

    public void removeStaff(String staffID) {
        List<Staff> staffList = new ArrayList<>(StaffRepository.load());
        Iterator<Staff> iterator = staffList.iterator();
        while (iterator.hasNext()) {
            Staff staff = iterator.next();
            if (staff.getUserID().equals(staffID)) {
                System.out.println("Staff "+staff.getUserID()+" successfully removed");
                iterator.remove();
                StaffRepository.store(staffList);
                return;
            }
        }
        System.out.println("No such staff member exists");
    }
}
