//NEED TO WRITE CUSTOM EXCEPTIONS

package filehandlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Admin;
import entities.Doctor;
import entities.Gender;
import entities.Pharmacist;
import entities.Staff;

public class StaffRepository {
    // file path to the staff database
    private static final String STAFF_CSV_FILE = "database\\StaffDatabase.csv";

    // load list of staff
    public static List<Staff> load() {
        List<Staff> staffList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(STAFF_CSV_FILE))) {
            String currentLine;
            br.readLine(); // skip header line

            while ((currentLine = br.readLine()) != null) {
                String[] data = currentLine.split(",");

                String staffID = data[0];
                String name = data[1];
                String role = data[2];
                Gender gender = Gender.fromString(data[3]);
                int age = Integer.parseInt(data[4]);
                String password = data[5];

                // Create new patient
                Staff staff = new Staff(staffID, password, name, gender, age, role);

                staffList.add(staff);
            }

            return staffList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // load single staff from CSV
    public static Staff load(String staffID) {
        try (BufferedReader br = new BufferedReader(new FileReader(STAFF_CSV_FILE))) {
            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                String[] data = currentLine.split(",");
                String storedStaffID = data[0];

                if (storedStaffID.equals(staffID)) {
                    String name = data[1];
                    String role = data[2];
                    Gender gender = Gender.fromString(data[3]);
                    int age = Integer.parseInt(data[4]);
                    String password = data[5];

                    switch (role.toLowerCase()) {
                        case "doctor":
                            return new Doctor(staffID, name, gender, age, password);
                        case "pharmacist":
                            return new Pharmacist(staffID, name, gender, age, password);
                        case "administrator":
                            return new Admin(staffID, name, gender, age, password);
                        default:
                            return new Staff(staffID, password, name, gender, age, role); // Default to generic staff
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // store list of staff to CSV
    public static void store(List<Staff> staffList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(STAFF_CSV_FILE))) {
            // Write header row
            bw.write("StaffID,Name,Role,Gender,Age,Password");
            bw.newLine();

            // Write each medication record to the CSV (overwrite)
            for (Staff staff : staffList) {
                String line = staff.getUserID() + "," + staff.getName() + "," + staff.getRole() + ","
                        + staff.getGender().toString() + "," + Integer.toString(staff.getAge()) + ","
                        + staff.getPassword();
                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static boolean doesStaffExist(String staffID) {
        try (BufferedReader br = new BufferedReader(new FileReader(STAFF_CSV_FILE))) {
            String currentLine;
            br.readLine(); // Skip the header row

            while ((currentLine = br.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (data[0].equals(staffID)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the staff database: " + e.getMessage());
        }
        return false;
    }
}
