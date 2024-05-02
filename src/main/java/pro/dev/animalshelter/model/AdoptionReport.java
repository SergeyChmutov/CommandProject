package pro.dev.animalshelter.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class AdoptionReport {
    @EmbeddedId
    private AdoptionReportPK pk;
    private String ration;
    private String wellBeing;
    private String behaviourChange;

    public AdoptionReport() {
    }

    public AdoptionReport(
            Adoption adoption,
            LocalDate reportDate,
            String ration,
            String wellBeing,
            String behaviourChange
    ) {
        this.pk = new AdoptionReportPK(adoption, reportDate);
        this.ration = ration;
        this.wellBeing = wellBeing;
        this.behaviourChange = behaviourChange;
    }

    public AdoptionReportPK getPk() {
        return pk;
    }

    public void setPk(AdoptionReportPK pk) {
        this.pk = pk;
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
        AdoptionReport that = (AdoptionReport) o;
        return Objects.equals(pk, that.pk)
                && Objects.equals(ration, that.ration)
                && Objects.equals(wellBeing, that.wellBeing)
                && Objects.equals(behaviourChange, that.behaviourChange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, ration, wellBeing, behaviourChange);
    }

    @Override
    public String toString() {
        return "AdoptionReport{" +
                "pk=" + pk +
                ", ration='" + ration + '\'' +
                ", wellBeing='" + wellBeing + '\'' +
                ", behaviourChange='" + behaviourChange + '\'' +
                '}';
    }
}
