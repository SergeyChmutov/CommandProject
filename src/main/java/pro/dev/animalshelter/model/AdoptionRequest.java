package pro.dev.animalshelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.util.Objects;
@Embeddable
public class AdoptionRequest {
    @OneToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @OneToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    public AdoptionRequest() {
    }

    public AdoptionRequest(Animal animal, Users user, Shelter shelter) {
        this.animal = animal;
        this.user = user;
        this.shelter = shelter;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdoptionRequest that = (AdoptionRequest) o;
        return Objects.equals(animal, that.animal) && Objects.equals(user, that.user) && Objects.equals(shelter, that.shelter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animal, user, shelter);
    }

    @Override
    public String toString() {
        return "AdoptionRequest{" +
                "animal=" + animal +
                ", user=" + user +
                ", shelter=" + shelter +
                '}';
    }
}
