package pro.dev.animalshelter.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    private AdoptionRequest adoptionRequest;

    private LocalDate confirmationDate;

    private LocalDate trialDate;

    public Adoption() {
    }

    public Adoption(Shelter shelter, Users user, Animal animal, LocalDate confirmationDate, LocalDate trialDate) {
        this.adoptionRequest = new AdoptionRequest(animal, user, shelter);
        this.confirmationDate = confirmationDate;
        this.trialDate = trialDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AdoptionRequest getAdoptionRequest() {
        return adoptionRequest;
    }

    public void setAdoptionRequest(AdoptionRequest adoptionRequest) {
        this.adoptionRequest = adoptionRequest;
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
        Adoption adoption = (Adoption) o;
        return id == adoption.id && Objects.equals(adoptionRequest, adoption.adoptionRequest) && Objects.equals(confirmationDate, adoption.confirmationDate) && Objects.equals(trialDate, adoption.trialDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adoptionRequest, confirmationDate, trialDate);
    }

    @Override
    public String toString() {
        return "Adoption{" +
                "id=" + id +
                ", adoptionRequest=" + adoptionRequest +
                ", confirmationDate=" + confirmationDate +
                ", trialDate=" + trialDate +
                '}';
    }
}
