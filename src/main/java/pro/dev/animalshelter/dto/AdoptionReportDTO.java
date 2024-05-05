package pro.dev.animalshelter.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class AdoptionReportDTO {
    private Long adoptionId;
    private LocalDate reportDate;
    private Long shelterId;
    private Long animalId;
    private Long userId;
    private String ration;
    private String wellBeing;
    private String behaviourChange;

    public AdoptionReportDTO() {
    }

    public AdoptionReportDTO(
            Long adoptionId,
            LocalDate reportDate,
            Long shelterId,
            Long animalId,
            Long userId,
            String ration,
            String wellBeing,
            String behaviourChange
    ) {
        this.adoptionId = adoptionId;
        this.reportDate = reportDate;
        this.shelterId = shelterId;
        this.animalId = animalId;
        this.userId = userId;
        this.ration = ration;
        this.wellBeing = wellBeing;
        this.behaviourChange = behaviourChange;
    }

    public Long getAdoptionId() {
        return adoptionId;
    }

    public void setAdoptionId(Long adoptionId) {
        this.adoptionId = adoptionId;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public Long getShelterId() {
        return shelterId;
    }

    public void setShelterId(Long shelterId) {
        this.shelterId = shelterId;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRation() {
        return ration;
    }

    public void setRation(String ration) {
        this.ration = ration;
    }

    public String getWellBeing() {
        return wellBeing;
    }

    public void setWellBeing(String wellBeing) {
        this.wellBeing = wellBeing;
    }

    public String getBehaviourChange() {
        return behaviourChange;
    }

    public void setBehaviourChange(String behaviourChange) {
        this.behaviourChange = behaviourChange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdoptionReportDTO that = (AdoptionReportDTO) o;
        return Objects.equals(adoptionId, that.adoptionId)
                && Objects.equals(reportDate, that.reportDate)
                && Objects.equals(shelterId, that.shelterId)
                && Objects.equals(animalId, that.animalId)
                && Objects.equals(userId, that.userId)
                && Objects.equals(ration, that.ration)
                && Objects.equals(wellBeing, that.wellBeing)
                && Objects.equals(behaviourChange, that.behaviourChange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                adoptionId,
                reportDate,
                shelterId,
                animalId,
                userId,
                ration,
                wellBeing,
                behaviourChange);
    }

    @Override
    public String toString() {
        return "AdoptionReportDTO{" +
                "adoptionId=" + adoptionId +
                ", reportDate=" + reportDate +
                ", shelterId=" + shelterId +
                ", animalId=" + animalId +
                ", userId=" + userId +
                ", ration='" + ration + '\'' +
                ", wellBeing='" + wellBeing + '\'' +
                ", behaviourChange='" + behaviourChange + '\'' +
                '}';
    }
}
