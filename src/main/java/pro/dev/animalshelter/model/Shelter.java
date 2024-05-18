package pro.dev.animalshelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(mappedBy = "shelter", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Users> volunteers;

    @OneToMany(mappedBy = "shelter")
    @JsonIgnore
    private List<Animal> animals;

    @OneToMany(mappedBy = "shelter")
//    @JsonManagedReference
    private List<Adoption> adoptions = new ArrayList<>();

    public Shelter() {
    }

    public Shelter(String name) {
        this.name = name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Users> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<Users> volunteers) {
        this.volunteers = volunteers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelter shelters = (Shelter) o;
        return id == shelters.id
                && Objects.equals(name, shelters.name)
                && Objects.equals(volunteers, shelters.volunteers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, volunteers);
    }

    @Override
    public String toString() {
        return "Shelters{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", volunteers=" + volunteers +
                '}';
    }
}
