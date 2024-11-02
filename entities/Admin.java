package entities;

import controllers.AppointmentManager;

public class Admin extends Staff {
    public Admin(String id, String name, Gender gender, int age, String pw){
        super(id, pw, name, gender, age, "administrator");
    }
    public void viewAllAppointments(AppointmentManager manager) {
        for (Appointment appointment : manager.getAllAppointments()) {
            System.out.println(appointment);
        }
    }
}
