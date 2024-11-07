package filehandlers;

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
            br.readLine();

            while ((currentLine = br.readLine()) != null) {
                String[] data = currentLine.split(",");

                String apptID = data[0];
                String patientID = data[1];
                String doctorID = data[2];
                LocalDate date = LocalDate.parse(data[3]);
                LocalTime time = LocalTime.parse(data[4]);
                ApptStatus status = ApptStatus.valueOf(data[5]);
                TypeOfService tos = TypeOfService.valueOf(data[6]);
                String consultationNotes = data[7];

                List<ApptPrescription> prescriptions = new ArrayList<>();
                if (data.length > 8 && !data[8].isEmpty()) {
                    String[] prescArray = data[8].split(";");
                    for (String prescData : prescArray) {
                        prescriptions.add(new ApptPrescription(prescData));
                    }
                }

                AOR aor = new AOR(apptID, patientID, doctorID, date, time, status, tos, consultationNotes,
                        prescriptions);
                aorList.add(aor);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return aorList;
    }

    public static void store(List<AOR> aorList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AOR_CSV_FILE))) {
            bw.write("apptID,patientID,doctorID,date,time,status,tos,consultationNotes,prescriptions");
            bw.newLine();

            for (AOR aor : aorList) {
                String line = aor.getApptID() + ","
                        + aor.getPatientID() + ","
                        + aor.getDoctorID() + ","
                        + aor.getDate() + ","
                        + aor.getTime() + ","
                        + aor.getStatus().toString() + ","
                        + aor.getTos().toString() + ","
                        + aor.getConsultationNotes() + ",";

                /*
                 * if (aor.getPrescriptions() != null && !aor.getPrescriptions().isEmpty()) {
                 * String prescriptions = String.join(";", aor.getPrescriptions().stream()
                 * .map(prescription -> prescription.getMedicationName())
                 * .toArray(String[]::new));
                 * line += prescriptions;
                 * }
                 */

                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
