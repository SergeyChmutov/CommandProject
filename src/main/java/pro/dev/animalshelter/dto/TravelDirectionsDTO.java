package pro.dev.animalshelter.dto;

import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import org.springframework.stereotype.Component;
import pro.dev.animalshelter.model.Shelter;

import java.util.Objects;

@Component
public class TravelDirectionsDTO {
    private Long shelterId;
    private Long fileSize;
    private String mediaType;

    public TravelDirectionsDTO() {
    }

    public TravelDirectionsDTO(Long shelterId, Long fileSize, String mediaType) {
        this.shelterId = shelterId;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
    }

    public Long getShelterId() {
        return shelterId;
    }

    public void setShelterId(Long shelterId) {
        this.shelterId = shelterId;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelDirectionsDTO that = (TravelDirectionsDTO) o;
        return Objects.equals(shelterId, that.shelterId) && Objects.equals(fileSize, that.fileSize) && Objects.equals(mediaType, that.mediaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shelterId, fileSize, mediaType);
    }

    @Override
    public String toString() {
        return "TravelDirectionsDTO{" +
                "shelterId=" + shelterId +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                '}';
    }
}
