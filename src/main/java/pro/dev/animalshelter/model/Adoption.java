package pro.dev.animalshelter.model;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.RequestBody;
import pro.dev.animalshelter.enums.RequestStatus;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;
    @Column(name = "request_status", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDate confirmationDate;

    private LocalDate trialDate;

    public Adoption() {
    }

    public Adoption(Shelter shelter, Users user, Animal animal) {
        this.shelter = shelter;
        this.user = user;
        this.animal = animal;
        this.status = RequestStatus.CONSIDERATION;
        this.confirmationDate = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
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
        return id == adoption.id && Objects.equals(user, adoption.user) && Objects.equals(animal, adoption.animal) && Objects.equals(shelter, adoption.shelter) && status == adoption.status && Objects.equals(confirmationDate, adoption.confirmationDate) && Objects.equals(trialDate, adoption.trialDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, animal, shelter, status, confirmationDate, trialDate);
    }

    @Override
    public String toString() {
        return "Adoption{" +
                "id=" + id +
                ", user=" + user +
                ", animal=" + animal +
                ", shelter=" + shelter +
                ", status=" + status +
                ", confirmationDate=" + confirmationDate +
                ", trialDate=" + trialDate +
                '}';
    }
}
