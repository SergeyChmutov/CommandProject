package pro.dev.animalshelter.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Objects;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnimal;
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    public Animal() {
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public Long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Long idAnimal) {
        this.idAnimal = idAnimal;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Animal(Long idAnimal, String name, int age, Shelter shelter) {
        this.idAnimal = idAnimal;
        this.name = name;
        this.age = age;
        this.shelter = shelter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age && Objects.equals(idAnimal, animal.idAnimal) && Objects.equals(name, animal.name) && Objects.equals(shelter, animal.shelter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnimal, name, age, shelter);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "idAnimal=" + idAnimal +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", shelter=" + shelter +
                '}';
    }
}