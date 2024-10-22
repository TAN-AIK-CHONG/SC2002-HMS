//NEED TO INCLUDE GETTERS AND SETTERS FOR ALL

package records;

public class StaffRecord implements IRecord{
    private String staffID;
    private String name;
    private String role;
    private Gender gender;
    private int age;
    private String password;

    public StaffRecord(String id, String name, String role, Gender gender, int age, String pw){
        this.staffID = id;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.password = pw;
    }

    public void view(){
        System.out.println("Staff ID: " + staffID);
        System.out.println("Name: " + name);
        System.out.println("Role: " + role);
        System.out.println("Gender: " + gender);
        System.out.println("Age: " + age);
    }

    //getters
    public String getStaffID(){
        return this.staffID;
    }

    public String getName(){
        return this.name;
    }

    public String getRole(){
        return this.role;
    }

    public Gender getGender(){
        return this.gender;
    }

    public int getAge(){
        return this.age;
    }

    public String getPassword(){
        return this.password;
    }
}


