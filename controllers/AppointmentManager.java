package controllers;

import java.time.LocalDate;
import java.time.LocalTime;

import entities.appointments.ApptSlot;

public class AppointmentManager {
    // FOR BOTH
    public void seeUpcoming(String userID){
        //sort by ID and by status confirmed
    }

    // FOR DOCTORS
    public void setAvailable(String doctorID, LocalDate date, LocalTime time){
        ApptSlot newSlot = new ApptSlot(date, time, doctorID);
        //store new slot into csv
    }

    public void seeAvailable(String doctorID){
        //sort by doctor ID and by status available
    }

    public void accept(String apptID){
        //set status to confirmed for that appt id
        //store in csv
    }

    public void decline(String apptID){
        //set status to cancelled for that appt id
        //store in csv
    }

    public void makeAOR(String apptID){
        //make AOR for that appt ID
        //store aor in csv
    }

    // FOR PATIENTS
    public void seeAvailable(){
        //sort by status available
    }

    public void schedule(String apptID, String patientID){
        //set status pending for that appt ID
        //set patient ID for that appt ID
    }

    public void reschedule(String prevApptID, String newApptID){
        //set status available for prev appt ID
        //set patient ID = null for prev appt ID
        //set status pending for new appt ID
    }

    public void cancel(String apptID){
        //set status to cancelled for that appt ID
        //store in csv
    }
    
}
