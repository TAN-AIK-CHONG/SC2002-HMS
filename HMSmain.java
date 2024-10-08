import java.util.Scanner;

public class HMSmain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please log in to the Hospital Management System");
        System.out.println("----------------------------------------------");

        boolean loggedIn = false;
        User loggedInUser = null;

        System.out.print("Are you a patient or staff? (Enter P for Patient, S for Staff): ");
        String userType = sc.nextLine().toUpperCase();
        boolean isPatient = userType.equals("P");

        while (!loggedIn) { 
            System.out.print("Hospital ID: ");
            int hospitalID = sc.nextInt();

            System.out.print("Password: ");
            String password = sc.nextLine(); 

            //LoginManager not implemented yet
            loggedInUser = LoginManager.authenticateUser(hospitalID, password, isPatient);

            if (loggedInUser != null) {
                loggedIn = true;  
                System.out.println("Login successful! Welcome to the system, " + loggedInUser.getUsername());
                loggedInUser.displayMenu();  
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        }

        sc.close();
    }
}
