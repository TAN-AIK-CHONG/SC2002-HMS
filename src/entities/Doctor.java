package entities;
public class Doctor extends Staff {
    public Doctor(String id, String name, Gender gender, int age, String pw){
        super(id, pw, name, gender, age, "doctor");
    }
}
