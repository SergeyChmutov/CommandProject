package pro.dev.animalshelter.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;
public class AvatarAnimal {
    @Id
    @GeneratedValue
    private  Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    private byte[] data;
    @OneToOne
    @JoinColumn(name = "animal_id")

    private Animal animal;

    @Override
    public String toString() {
        return "AvatarAnimal{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaTupe='" + mediaType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", animal=" + animal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvatarAnimal that = (AvatarAnimal) o;
        return fileSize == that.fileSize && Objects.equals(id, that.id) && Objects.equals(filePath, that.filePath) && Objects.equals(mediaType, that.mediaType) && Objects.deepEquals(data, that.data) && Objects.equals(animal, that.animal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filePath, fileSize, mediaType, Arrays.hashCode(data), animal);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
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

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
