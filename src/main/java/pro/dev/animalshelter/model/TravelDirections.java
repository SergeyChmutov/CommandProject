package pro.dev.animalshelter.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class TravelDirections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;
    @OneToOne
    private Shelter shelter;

    public TravelDirections() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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
        TravelDirections that = (TravelDirections) o;
        return Objects.equals(id, that.id) && Objects.equals(fileSize, that.fileSize) && Objects.equals(mediaType, that.mediaType) && Objects.deepEquals(data, that.data) && Objects.equals(shelter, that.shelter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileSize, mediaType, Arrays.hashCode(data), shelter);
    }

    @Override
    public String toString() {
        return "TravelDirections{" +
                "id=" + id +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", shelter=" + shelter +
                '}';
    }
}
