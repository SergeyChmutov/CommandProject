package pro.dev.animalshelter.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.dev.animalshelter.exception.AdoptionReportAnimalPhotoNotFound;
import pro.dev.animalshelter.exception.AdoptionReportWrongReportDateException;
import pro.dev.animalshelter.interfaces.AdoptionReportAnimalPhotoInterface;
import pro.dev.animalshelter.interfaces.AdoptionReportInterface;
import pro.dev.animalshelter.model.Adoption;
import pro.dev.animalshelter.model.AdoptionReport;
import pro.dev.animalshelter.model.AdoptionReportAnimalPhoto;
import pro.dev.animalshelter.model.AdoptionReportPK;
import pro.dev.animalshelter.repository.AdoptionReportAnimalPhotoRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import static pro.dev.animalshelter.constant.Constants.REPORT_DATE_FORMATTER;

@Service
public class AdoptionReportAnimalPhotoService implements AdoptionReportAnimalPhotoInterface {
    private final AdoptionReportAnimalPhotoRepository repository;
    private final AdoptionReportInterface adoptionReport;

    public AdoptionReportAnimalPhotoService(
            AdoptionReportAnimalPhotoRepository repository,
            AdoptionReportInterface adoptionReport
    ) {
        this.repository = repository;
        this.adoptionReport = adoptionReport;
    }

    @Override
    public AdoptionReportAnimalPhoto getAnimalPhoto(Long adoptionId, LocalDate reportDate) {
        Adoption adoption = new Adoption();
        adoption.setId(adoptionId);

        AdoptionReportPK pk = new AdoptionReportPK(adoption, reportDate);

        return repository.findByReport_Pk(pk).orElseThrow(
                () -> new AdoptionReportAnimalPhotoNotFound(
                        "Не найдено фото животного для Усыновления с идентификатором " + adoptionId + " и датой отчета " + reportDate
                )
        );
    }

    @Override
    public void uploadAnimalPhoto(
            Long adoptionId,
            LocalDate reportDate,
            MultipartFile travelDirectionsFile
    ) throws IOException {
        final AdoptionReportAnimalPhoto adoptionReport = findAdoptionReportAnimalPhotoOrCreateWhenNotFound(
                adoptionId,
                reportDate
        );
        adoptionReport.setFileSize(travelDirectionsFile.getSize());
        adoptionReport.setMediaType(travelDirectionsFile.getContentType());
        adoptionReport.setData(travelDirectionsFile.getBytes());
        repository.save(adoptionReport);
    }

    @Override
    public ResponseEntity<byte[]> downloadAnimalPhotoFromDb(Long adoptionId, LocalDate reportDate) {
        AdoptionReportAnimalPhoto animalPhoto = getAnimalPhoto(adoptionId, reportDate);
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType(animalPhoto.getMediaType()));
        headers.setContentLength(animalPhoto.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(animalPhoto.getData());
    }

    @Override
    public byte[] downloadAnimalPhotoDataFromDb(Long adoptionId, LocalDate reportDate) {
        AdoptionReportAnimalPhoto animalPhoto = getAnimalPhoto(adoptionId, reportDate);
        return animalPhoto.getData();
    }

    @Override
    public AdoptionReportAnimalPhoto saveAnimalPhoto(AdoptionReportPK pk, byte[] photoData, Long fileSize) {
        AdoptionReportAnimalPhoto savedPhotoReport = findAdoptionReportAnimalPhotoOrCreateWhenNotFound(
                pk.getAdoption().getId(),
                pk.getReportDate());
        savedPhotoReport.setFileSize(fileSize);
        savedPhotoReport.setMediaType(MediaType.IMAGE_JPEG_VALUE);
        savedPhotoReport.setData(photoData);
        repository.save(savedPhotoReport);
        return savedPhotoReport;
    }

    @Override
    public ResponseEntity<byte[]> downloadAnimalPhotoReportFromDb(Long adoptionId, String reportDateText) {
        LocalDate reportDate;
        try {
            reportDate = LocalDate.parse(reportDateText, REPORT_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new AdoptionReportWrongReportDateException("Ошибка в указании даты отчета: " + reportDateText);
        }

        return downloadAnimalPhotoFromDb(adoptionId, reportDate);
    }

    private AdoptionReportAnimalPhoto findAdoptionReportAnimalPhotoOrCreateWhenNotFound(
            Long adoptionId,
            LocalDate reportDate
    ) {
        Optional<AdoptionReport> report = adoptionReport.getAdoptionReport(adoptionId, reportDate);

        if (report.isPresent()) {
            return repository.findByReport(report.get()).orElse(new AdoptionReportAnimalPhoto(report.get()));
        } else {
            AdoptionReport savedReport = adoptionReport.createAdoptionReport(
                    adoptionId,
                    reportDate,
                    null,
                    null,
                    null
            );
            return new AdoptionReportAnimalPhoto(savedReport);
        }
    }
}
