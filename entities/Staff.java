package entities;

public class Staff extends User {
    int age;
    String role;

    public Staff(String userID, String password, String name, Gender gender, int age, String role) {
        super(userID, password, name, gender);
        this.age = age;
        this.role = role;
    }

    public void viewRecords() {
        System.out.println("Staff ID: " + super.getUserID());
        System.out.println("Name: " + super.getName());
        System.out.println("Gender: " + super.getGender().toString());
        System.out.println("Age: " + this.age);
        System.out.println("Role: " + this.role);
    }

    // setters and getters
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
