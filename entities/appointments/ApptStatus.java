package entities.appointments;

public enum ApptStatus {
    AVAILABLE, PENDING, CONFIRMED, CANCELLED, COMPLETED;

    public static ApptStatus fromString(String statusStr) {
        switch (statusStr.toLowerCase()) {
            case "available":
                return AVAILABLE;
            case "pending":
                return PENDING;
            case "confirmed":
                return CONFIRMED;
            case "cancelled":
                return CANCELLED;
            case "completed":
                return COMPLETED;
            default:
                throw new IllegalArgumentException("Unknown status: " + statusStr);
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case AVAILABLE:
                return "Available";
            case PENDING:
                return "Pending";
            case CONFIRMED:
                return "Confirmed";
            case CANCELLED:
                return "Cancelled";
            case COMPLETED:
                return "Completed";
            default:
                throw new AssertionError("Unknown status: " + this);
        }
    }
}
