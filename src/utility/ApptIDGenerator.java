package utility;

import java.security.SecureRandom;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import entities.appointments.ApptSlot;
import filehandlers.ApptSlotRepository;

public class ApptIDGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateUniqueID(int length) {
        List<ApptSlot> existingAppointments = ApptSlotRepository.load();
        Set<String> existingIDs = existingAppointments.stream()
                .map(ApptSlot::getApptID)
                .collect(Collectors.toSet());

        String id;
        do {
            id = generateID(length);
        } while (existingIDs.contains(id)); // Keep generating until a unique ID is found

        return id;
    }

    private static String generateID(int length) {
        StringBuilder id = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            id.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return id.toString();
    }
}
