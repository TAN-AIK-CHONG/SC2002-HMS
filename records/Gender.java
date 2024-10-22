package records;

public enum Gender {
    MALE, FEMALE;

    public static Gender fromString(String genderStr) {
        switch (genderStr.toLowerCase()) {
            case "male":
                return MALE;
            case "female":
                return FEMALE;
            default:
                throw new IllegalArgumentException("Unknown gender: " + genderStr);
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case MALE:
                return "Male";
            case FEMALE:
                return "Female";
            default:
                throw new AssertionError("Unknown gender: " + this);
        }
    }
}