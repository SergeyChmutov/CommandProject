package pro.dev.animalshelter.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import pro.dev.animalshelter.model.AdoptionReportAnimalPhoto;
import pro.dev.animalshelter.model.AdoptionReportPK;

import java.io.IOException;
import java.time.LocalDate;

public interface AdoptionReportAnimalPhotoInterface {
    AdoptionReportAnimalPhoto getAnimalPhoto(Long adoptionId, LocalDate reportDate);

    void uploadAnimalPhoto(Long adoptionId, LocalDate reportDate, MultipartFile travelDirectionsFile) throws IOException;

    ResponseEntity<byte[]> downloadAnimalPhotoFromDb(Long adoptionId, LocalDate reportDate);

    byte[] downloadAnimalPhotoDataFromDb(Long adoptionId, LocalDate reportDate);

    AdoptionReportAnimalPhoto saveAnimalPhoto(AdoptionReportPK pk, byte[] photoData, Long fileSize);

    ResponseEntity<byte[]> downloadAnimalPhotoReportFromDb(Long adoptionId, String reportDate);
}
