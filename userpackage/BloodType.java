package userpackage;

public enum BloodType {
    A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE, AB_POSITIVE, AB_NEGATIVE, O_POSITIVE, O_NEGATIVE;

    public static BloodType fromString(String bloodTypeStr) {
        switch (bloodTypeStr.toUpperCase()) {
            case "A+":
                return A_POSITIVE;
            case "A-":
                return A_NEGATIVE;
            case "B+":
                return B_POSITIVE;
            case "B-":
                return B_NEGATIVE;
            case "AB+":
                return AB_POSITIVE;
            case "AB-":
                return AB_NEGATIVE;
            case "O+":
                return O_POSITIVE;
            case "O-":
                return O_NEGATIVE;
            default:
                throw new IllegalArgumentException("Unknown blood type: " + bloodTypeStr);
        }
    }
}