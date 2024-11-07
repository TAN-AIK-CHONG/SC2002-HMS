package filehandlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import entities.appointments.ApptSlot;
import entities.appointments.ApptStatus;

public class ApptSlotRepository {
    // File path to appointment schedules
    private static final String APPT_CSV_FILE = "database\\AppointmentSchedulesDatabase.csv";

    // Load all appointments from the CSV file
    public static List<ApptSlot> load() {
        List<ApptSlot> slots = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(APPT_CSV_FILE))) {
            br.readLine(); // skip the header

            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                String[] data = currentLine.split(",");
                String apptID = data[0];
                LocalDate date = LocalDate.parse(data[1]);
                LocalTime time = LocalTime.parse(data[2]);
                String doctorID = data[3];
                String patientID = data[4];
                ApptStatus status = ApptStatus.fromString(data[5]);

                ApptSlot appointment = new ApptSlot(apptID, date, time, doctorID, patientID, status);
                slots.add(appointment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return slots;
    }

    // Store all appointments back to CSV
    public static void store(List<ApptSlot> slots) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(APPT_CSV_FILE))) {
            // Write header row
            bw.write("AppointmentID,Date,Time,DoctorID,PatientID,Status");
            bw.newLine();

            // Write each medication record to the CSV (overwrite)
            for (ApptSlot appt : slots) {
                String line = appt.getApptID() + "," + appt.getDate().toString() + "," + appt.getTime().toString() + ","
                        + appt.getDoctorID() + "," + appt.getPatientID() + "," + appt.getStatus().toString();
                bw.write(line);
                bw.newLine();
            }

            System.out.println("Appointment Schedule saved successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
