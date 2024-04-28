package pro.dev.animalshelter.dto;

import java.util.Objects;

public class AvatarAnimalDTO {
    private Long id;

    private long fileSize;

    private String mediaType;

    private long animalId;

    public AvatarAnimalDTO() {
        this.id = id;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.animalId = animalId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(long animalId) {
        this.animalId = animalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvatarAnimalDTO that = (AvatarAnimalDTO) o;
        return fileSize == that.fileSize && animalId == that.animalId && Objects.equals(id, that.id) && Objects.equals(mediaType, that.mediaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileSize, mediaType, animalId);
    }

    @Override
    public String toString() {
        return "AvatarAnimalDTO{" +
                "id=" + id +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", animalId=" + animalId +
                '}';
    }
}

