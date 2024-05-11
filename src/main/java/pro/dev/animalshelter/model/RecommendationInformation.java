package pro.dev.animalshelter.model;

import jakarta.persistence.*;
import pro.dev.animalshelter.enums.RecommendationType;

import java.util.Objects;

@Entity
@Table(name = "recommendations")
public class RecommendationInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "recommendation_type", nullable = false, unique = true)
    private RecommendationType type;
    private String information;

    public RecommendationInformation() {
    }

    public RecommendationInformation(RecommendationType type, String information) {
        this.type = type;
        this.information = information;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecommendationType getType() {
        return type;
    }

    public void setType(RecommendationType type) {
        this.type = type;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendationInformation that = (RecommendationInformation) o;
        return Objects.equals(id, that.id) && type == that.type && Objects.equals(information, that.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, information);
    }

    @Override
    public String toString() {
        return "RecommendationInformation{" +
                "id=" + id +
                ", type=" + type +
                ", information='" + information + '\'' +
                '}';
    }
}
