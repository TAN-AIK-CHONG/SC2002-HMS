package utilitypackage;

import userpackage.Gender;

public class StaffRecords {
    private String staffID;
    private String name;
    private String role;
    private Gender gender;
    private int age;
    private String password;

    public StaffRecords(String id, String name, String role, Gender gender, int age, String pw){
        this.staffID = id;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.password = pw;
    }
    
    //getters
    public String getStaffID(){
        return this.staffID;
    }

    public String getPassword(){
        return this.password;
    }

    public String getRole(){
        return this.role;
    }

    public void viewRecord(){
        System.out.println("Staff ID: " + staffID);
        System.out.println("Name: " + name);
        System.out.println("Role: " + role);
        System.out.println("Gender: " + gender);
        System.out.println("Age: " + age);
    }
}
