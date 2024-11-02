package controllers;

import dbinterfaces.PatientRepository;
import dbinterfaces.StaffRepository;
import entities.Staff;

//METHODS WILL BE IMPLEMENTED BY AC SOON

public class StaffManager extends UserManager{
    private StaffRepository staffRepository;

    //constructor
    public StaffManager(StaffRepository staffRepository) {
        super(staffRepository);
        this.staffRepository = staffRepository;
    }
}
