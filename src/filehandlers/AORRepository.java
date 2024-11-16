package filehandlers;

import entities.PrescriptionStatus;
import entities.appointments.AOR;
import entities.appointments.ApptPrescription;
import entities.appointments.ApptStatus;
import entities.appointments.TypeOfService;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AORRepository {
    private static final String AOR_CSV_FILE = "database\\AORDatabase.csv";

    public static List<AOR> load() {
        List<AOR> aorList = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(AOR_CSV_FILE))) {
            String currentLine;
    
            // Skip the header line
            br.readLine();
    
            while ((currentLine = br.readLine()) != null) {
                String[] data = currentLine.split(",", -1); // Use -1 to include empty fields
    
                String apptID = data[0];
                String patientID = data[1];
                String doctorID = data[2];
                LocalDate date = LocalDate.parse(data[3]);
                LocalTime time = LocalTime.parse(data[4]);
                ApptStatus status = ApptStatus.fromString(data[5]);
                TypeOfService tos = TypeOfService.fromString(data[6]);
                String consultationNotes = data[7];
    
                // Parse the prescriptions field
                List<ApptPrescription> prescriptions = new ArrayList<>();
                if (data.length > 8 && !data[8].isEmpty()) { // Check if prescriptions field exists and is not empty
                    String[] prescriptionEntries = data[8].split(";");
                    for (String entry : prescriptionEntries) {
                        String[] parts = entry.split("\\|");
                        if (parts.length == 2) { // Ensure both name and status exist
                            String name = parts[0];
                            String statusStr = parts[1];
                            PrescriptionStatus prescriptionStatus = PrescriptionStatus.fromString(statusStr);
                            prescriptions.add(new ApptPrescription(name, prescriptionStatus));
                        }
                    }
                }
    
                // Create the AOR object
                AOR aor = new AOR(apptID, patientID, doctorID, date, time, status, tos, consultationNotes, prescriptions);
                aorList.add(aor);
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error parsing file: " + e.getMessage());
            e.printStackTrace();
        }
    
        return aorList;
    }


    public static void store(List<AOR> aorList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AOR_CSV_FILE))) {
            bw.write("apptID,patientID,doctorID,date,time,status,tos,consultationNotes,prescriptions");
            bw.newLine();

            for (AOR aor : aorList) {
                StringBuilder prescriptionString = new StringBuilder();
            
            // Iterate through each prescription in the list and append "name|status"
            for (ApptPrescription prescription : aor.getPrescriptions()){
                if (prescriptionString.length() > 0) {
                    prescriptionString.append(";"); // Add a semicolon between prescriptions
                }
                prescriptionString.append(prescription.getMedicationName()).append("|").append(prescription.getStatus());
            }
                String line = aor.getApptID() + ","
                        + aor.getPatientID() + ","
                        + aor.getDoctorID() + ","
                        + aor.getDate() + ","
                        + aor.getTime() + ","
                        + aor.getStatus().toString() + ","
                        + aor.getTos().toString() + ","
                        + aor.getConsultationNotes() + ","
                        + prescriptionString;

                /*if (aor.getPrescriptions() != null && !aor.getPrescriptions().isEmpty()) {
                    String prescriptions = String.join(";", aor.getPrescriptions().stream()
                            .map(prescription -> prescription.getMedicationName())
                            .toArray(String[]::new));
                    line += prescriptions;
                }*/

                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
