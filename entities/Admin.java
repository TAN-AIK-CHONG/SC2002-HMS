package entities;

public class Admin extends Staff {
    public Admin(String id, String name, Gender gender, int age, String pw) {
        super(id, pw, name, gender, age, "administrator");
    }
}
