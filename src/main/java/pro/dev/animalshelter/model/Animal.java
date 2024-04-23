package pro.dev.animalshelter.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idAnimal;
    private String nameAnimal;
    private int ageAnimal;
    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    public Animal(Long idAnimal, String nameAnimal, int ageAnimal) {
    }

    public Animal(Long idAnimal, String nameAnimal, int ageAnimal, Shelter shelter) {
        this.idAnimal = idAnimal;
        this.nameAnimal = nameAnimal;
        this.ageAnimal = ageAnimal;
        this.shelter = shelter;
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
        return nameAnimal;
    }

    public void setName(String name) {
        this.nameAnimal = nameAnimal;
    }

    public int getAge() {
        return ageAnimal;
    }

    public void setAge(int age) {
        this.ageAnimal = ageAnimal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return ageAnimal == animal.ageAnimal
                && Objects.equals(idAnimal, animal.idAnimal)
                && Objects.equals(nameAnimal, animal.nameAnimal)
                && Objects.equals(shelter, animal.shelter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnimal, nameAnimal, ageAnimal, shelter);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "idAnimal=" + idAnimal +
                ", name='" + nameAnimal + '\'' +
                ", age=" + ageAnimal +
                ", shelter=" + shelter +
                '}';
    }
}