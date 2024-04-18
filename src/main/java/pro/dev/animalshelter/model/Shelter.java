package pro.dev.animalshelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(mappedBy = "shelter")
    @JsonIgnore
    private List<Users> volunteers;

    @OneToMany(mappedBy = "shelter")
    @JsonIgnore
    private List<Animal> animals;

    public Shelter() {
    }

    public Shelter(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        return id == shelters.id && Objects.equals(name, shelters.name) && Objects.equals(volunteers, shelters.volunteers);
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
