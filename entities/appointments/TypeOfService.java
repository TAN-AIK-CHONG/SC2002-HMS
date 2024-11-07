package entities.appointments;

public enum TypeOfService {
    CONSULTATION, BLOODTEST, XRAY, PHYSICALTHERAPY, VACCINATION, EKG;

    public static TypeOfService fromString(String serviceStr) {
        switch (serviceStr.toLowerCase()) {
            case "consultation":
                return CONSULTATION;
            case "blood test":
                return BLOODTEST;
            case "x-ray":
                return XRAY;
            case "physicaltherapy":
                return PHYSICALTHERAPY;
            case "vaccination":
                return VACCINATION;
            case "ekg":
                return EKG;
            default:
                throw new IllegalArgumentException("Unknown service type: " + serviceStr);
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case CONSULTATION:
                return "Consultation";
            case BLOODTEST:
                return "Blood Test";
            case XRAY:
                return "X-Ray";
            case PHYSICALTHERAPY:
                return "Physical Therapy";
            case VACCINATION:
                return "Vaccination";
            case EKG:
                return "EKG";
            default:
                throw new AssertionError("Unknown service type: " + this);
        }
    }
}
