package utilitypackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import userpackage.Gender;

public class StaffRecordsManager {
    public static StaffRecords loadRecords(String staffID){
        String filePath = "database\\StaffList.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String currentLine;

            while((currentLine=br.readLine())!= null){
                String[] data = currentLine.split(",");
                String storedStaffID = data[0];

                if(storedStaffID.equals(staffID)){
                    String name = data[1];
                    String role = data[2];
                    Gender gender = Gender.fromString(data[3]);
                    int age = Integer.parseInt(data[4]);
                    String password = data[5];

                    StaffRecords staffInfo = new StaffRecords(storedStaffID, name, role, gender, age, password);
                    return staffInfo;
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
