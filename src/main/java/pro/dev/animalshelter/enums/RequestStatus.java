package pro.dev.animalshelter.enums;

public enum RequestStatus {
    CONSIDERATION("CONSIDERATION"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    REJECTED_DURING_TRIAL_DATE("REJECTED-DURING-TRIAL-DATE"),
    ;

    private final String statusName;

    RequestStatus(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
