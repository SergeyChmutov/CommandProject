package pro.dev.animalshelter.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class AdoptionReportAnimalPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;
    @OneToOne
    private AdoptionReport report;

    public AdoptionReportAnimalPhoto() {
    }

    public AdoptionReportAnimalPhoto(AdoptionReport report, Long fileSize, String mediaType, byte[] data) {
        this.report = report;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.data = data;
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

    public AdoptionReport getReport() {
        return report;
    }

    public void setReport(AdoptionReport report) {
        this.report = report;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdoptionReportAnimalPhoto that = (AdoptionReportAnimalPhoto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(fileSize, that.fileSize)
                && Objects.equals(mediaType, that.mediaType)
                && Objects.deepEquals(data, that.data)
                && Objects.equals(report, that.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileSize, mediaType, Arrays.hashCode(data), report);
    }

    @Override
    public String toString() {
        return "AnimalReportPhoto{" +
                "id=" + id +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", report=" + report +
                '}';
    }
}
