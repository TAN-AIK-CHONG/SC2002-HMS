package entities;

public class Pharmacist extends Staff {
    public Pharmacist(String id, String name, Gender gender, int age, String pw) {
        super(id, pw, name, gender, age, "pharmacist");
    }
}
