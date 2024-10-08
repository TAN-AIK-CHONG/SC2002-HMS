import java.util.Scanner;

public class HMSmain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please log in to the Hospital Management System");
        System.out.println("----------------------------------------------");

        boolean loggedIn = false;

        System.out.print("Are you a patient or staff? (Enter P for Patient, S for Staff): ");
        String userType = sc.nextLine().toUpperCase();
        boolean isPatient = userType.equals("P");

        while (!loggedIn) { 
            System.out.print("Hospital ID: ");
            String hospitalID = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine(); 

            
            loggedIn = LoginManager.authenticateUser(hospitalID, password, isPatient);

            if (loggedIn) {  
                //temporarily only have patient. implement rest later.
                Patient newPatient = new Patient(hospitalID, password, MedicalRecordsManager.loadRecords(hospitalID));
                System.out.println("Login successful! Welcome to the system.");
                newPatient.displayMenu();  
                int choice = sc.nextInt();
                sc.nextLine();
                while(choice!=9){
                    switch (choice){
                        case 1: 
                            newPatient.viewMedicalRecords();
                            break;
                        case 2:
                            System.out.println("What is your new email address?");
                            String newAdd = sc.nextLine();
                            System.out.println("What is your new phone number?");
                            String newNumber = sc.nextLine();
                            newPatient.updateInfo(newAdd, newNumber);
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                        case 8:
                            break;
                        case 9:
                            loggedIn = false;
                            break;
                    }
                    System.out.print("Choose another option...");
                    choice = sc.nextInt();
                    sc.nextLine();
                }   
            }
            else {
                System.out.println("Invalid credentials. Please try again.");
            }
        }

        sc.close();
    }
}