package pro.dev.animalshelter.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class AdoptionReportPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "adoption_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_adoption_id"))
    private Adoption adoption;
    @Column(name = "report_date", insertable = false, updatable = false)
    private LocalDate reportDate;

    private AdoptionReportPK() {
    }

    public AdoptionReportPK(Adoption adoption, LocalDate reportDate) {
        this.adoption = adoption;
        this.reportDate = reportDate;
    }

    public Adoption getAdoption() {
        return adoption;
    }

    public void setAdoption(Adoption adoption) {
        this.adoption = adoption;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdoptionReportPK that = (AdoptionReportPK) o;
        return Objects.equals(adoption, that.adoption) && Objects.equals(reportDate, that.reportDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adoption, reportDate);
    }

    @Override
    public String toString() {
        return "AdoptionReportPK{" +
                "adoption = " + adoption +
                ", reportDate = " + reportDate +
                '}';
    }
}
