package pro.dev.animalshelter.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Users {
    @Id
    Long id;
    String name;
    String phone;
    @ManyToOne
    @JoinColumn(name = "shelter_id")
    Shelter shelter;


    public Users() {
    }

    public Users(Long id, String name, String phone, Shelter shelter) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.shelter = shelter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) && Objects.equals(name, users.name) && Objects.equals(phone, users.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", shelter=" + shelter +
                '}';
    }
}
