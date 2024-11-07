package utility;

import java.security.MessageDigest;

public class Hash {

    public static String hashWith256(String text) {
        try {
            // Get a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Apply the hash function to the input text and get the byte array
            byte[] hashBytes = digest.digest(text.getBytes("UTF-8"));

            // Convert the byte array to hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            // Return the resulting hash as a string
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
