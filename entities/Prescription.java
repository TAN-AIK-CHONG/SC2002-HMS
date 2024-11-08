package entities;

import java.util.zip.DeflaterInputStream;

public enum Prescription {
    PENDING, DISPENSED;

    public static Prescription fromString(String prescriptionString) {
        switch (prescriptionString.toLowerCase()) {
            case "pending":
                return PENDING;
            case "dispensed":
                return DISPENSED;
            default:
                throw new IllegalArgumentException("Unknown prescription: " + prescriptionString);
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case PENDING:
                return "PENDING";
            case DISPENSED:
                return "DISPENSED";
            default:
                throw new AssertionError("Unknown prescription: " + this);
        }
    }
}

