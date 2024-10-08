public abstract class User {
    private int username;
    private String password;
    private String role; 

    public User(int username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public abstract void displayMenu();
}
