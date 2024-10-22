package userPackage;

import records.StaffRecord;

public class Doctor implements IMenu{
    private StaffRecord record;

    public Doctor(StaffRecord record){
        this.record = record;
    }

    public void displayMenu(){
        System.out.println("not implemented yet");
    }

    //temp to check compilation issue
    public void viewRecord(){
        this.record.view();
    }
}
