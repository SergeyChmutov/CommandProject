package pro.dev.animalshelter.dto;

import pro.dev.animalshelter.enums.RequestStatus;

import java.time.LocalDate;
import java.util.Objects;

public class AdoptionDTO {
    private Long userId;

    private Long animalId;

    private Long shelterId;

    private RequestStatus status;

    private LocalDate confirmationDate;

    private LocalDate trialDate;

    public AdoptionDTO(Long userId, Long animalId, Long shelterId, RequestStatus status, LocalDate confirmationDate, LocalDate trialDate) {
        this.userId = userId;
        this.animalId = animalId;
        this.shelterId = shelterId;
        this.status = status;
        this.confirmationDate = confirmationDate;
        this.trialDate = trialDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public Long getShelterId() {
        return shelterId;
    }

    public void setShelterId(Long shelterId) {
        this.shelterId = shelterId;
    }

    public RequestStatus getStatuses() {
        return status;
    }

    public void setStatuses(RequestStatus statuses) {
        this.status = statuses;
    }

    public LocalDate getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(LocalDate confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public LocalDate getTrialDate() {
        return trialDate;
    }

    public void setTrialDate(LocalDate trialDate) {
        this.trialDate = trialDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdoptionDTO that = (AdoptionDTO) o;
        return Objects.equals(userId, that.userId) && Objects.equals(animalId, that.animalId) && Objects.equals(shelterId, that.shelterId) && status == that.status && Objects.equals(confirmationDate, that.confirmationDate) && Objects.equals(trialDate, that.trialDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, animalId, shelterId, status, confirmationDate, trialDate);
    }

    @Override
    public String toString() {
        return "AdoptionDTO{" +
                "userId=" + userId +
                ", animalId=" + animalId +
                ", shelterId=" + shelterId +
                ", statuses=" + status +
                ", confirmationDate=" + confirmationDate +
                ", trialDate=" + trialDate +
                '}';
    }
}
