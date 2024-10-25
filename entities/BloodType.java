package entities;

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

    @Override
    public String toString() {
        switch (this) {
            case A_POSITIVE:
                return "A+";
            case A_NEGATIVE:
                return "A-";
            case B_POSITIVE:
                return "B+";
            case B_NEGATIVE:
                return "B-";
            case AB_POSITIVE:
                return "AB+";
            case AB_NEGATIVE:
                return "AB-";
            case O_POSITIVE:
                return "O+";
            case O_NEGATIVE:
                return "O-";
            default:
                throw new AssertionError("Unknown blood type: " + this);
        }
    }
}