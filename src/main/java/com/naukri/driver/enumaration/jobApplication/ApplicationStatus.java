package com.naukri.driver.enumaration.jobApplication;

public enum ApplicationStatus {
    APPLIED,
    UNDER_REVIEW,
    SHORTLISTED,
    INTERVIEW,
    SELECTED,
    REJECTED,
    WITHDRAWN;

    public boolean canTransitionTo(ApplicationStatus newStatus) {
        if (newStatus == null) {
            return false;
        }
        if (this == newStatus) {
            return false;
        }
        if (newStatus == WITHDRAWN && this != SELECTED && this != REJECTED) {
            return true;
        }

        // Define valid state transitions using a clean switch statement
        return switch (this) {
            case APPLIED ->      newStatus == UNDER_REVIEW || newStatus == REJECTED;
            case UNDER_REVIEW -> newStatus == SHORTLISTED || newStatus == REJECTED;
            case SHORTLISTED ->  newStatus == INTERVIEW   || newStatus == REJECTED;
            case INTERVIEW ->    newStatus == SELECTED    || newStatus == REJECTED;
            default -> false;
        };
    }
}
