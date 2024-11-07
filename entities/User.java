package entities;

import utility.Hash;

public abstract class User {
    private String userID;
    private String password;
    private String name;
    private Gender gender;

    public User(String userID, String password, String name, Gender gender) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.gender = gender;
    }

    public boolean authenticate(String InputPW) {
        return Hash.hashWith256(InputPW).equals(this.password);
    }

    public boolean isDefault() {
        return Hash.hashWith256("password").equals(this.password);
    }

    public abstract void viewRecords();

    // getters and setters
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Hash.hashWith256(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
