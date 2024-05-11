package pro.dev.animalshelter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import pro.dev.animalshelter.enums.RecommendationType;
import pro.dev.animalshelter.exception.RecommendationInformationNotFoundException;
import pro.dev.animalshelter.exception.RecommendationTypeErrorNameException;
import pro.dev.animalshelter.interfaces.RecommendationInformationInterface;
import pro.dev.animalshelter.model.RecommendationInformation;
import pro.dev.animalshelter.repository.RecommendationInformationRepository;

import java.util.Collection;

@Service
public class RecommendationInformationService implements RecommendationInformationInterface {
    private final RecommendationInformationRepository repository;

    public RecommendationInformationService(RecommendationInformationRepository repository) {
        this.repository = repository;
    }

    @Override
    public RecommendationInformation createRecommendationInformation(String typeName, String information) {
        RecommendationInformation recommendationInformation = getInformationByTypeNameOrCreateIfNotExist(typeName);
        recommendationInformation.setInformation(information);
        return repository.save(recommendationInformation);
    }

    @Override
    public RecommendationInformation updateRecommendationInformation(String typeName, String information) {
        RecommendationInformation savedInformation = getInformationBy(typeName);
        savedInformation.setInformation(information);
        return repository.save(savedInformation);
    }

    @Override
    public RecommendationInformation deleteRecommendationInformation(String typeName) {
        RecommendationInformation savedInformation = getInformationBy(typeName);
        repository.deleteById(savedInformation.getId());
        return savedInformation;
    }

    @Override
    public RecommendationInformation getInformationBy(String typeName) {
        String validTypeName = typeName.trim().toUpperCase();
        final RecommendationType recommendationType = RecommendationType.getTypeByName(validTypeName);
        Optional<RecommendationInformation> savedInformation = repository.findByType(recommendationType);
        if (savedInformation.isPresent()) {
            return savedInformation.get();
        } else {
            throw new RecommendationInformationNotFoundException(typeName);
        }
    }

    @Override
    public Collection<RecommendationInformation> getAllRecommendationInformation() {
        return repository.findAll();
    }

    @Override
    public Collection<String> getRecommendationTypesNameList() {
        return RecommendationType.getTypesList().stream().map(String::toLowerCase).toList();
    }

    private RecommendationInformation getInformationByTypeNameOrCreateIfNotExist(String typeName) {
        String validTypeName = typeName.trim().toUpperCase();
        final RecommendationType recommendationType = RecommendationType.getTypeByName(validTypeName);
        Optional<RecommendationInformation> savedInformation = repository.findByType(recommendationType);
        if (savedInformation.isPresent()) {
            return savedInformation.get();
        } else {
            RecommendationInformation information = new RecommendationInformation();
            information.setType(recommendationType);
            return information;
        }
    }

    private RecommendationType getRecommendationTypeByName(String typeName) {
        RecommendationType recommendationType = RecommendationType.getTypeByName(typeName.trim().toUpperCase());
        if (recommendationType == null) {
            throw new RecommendationTypeErrorNameException("Ошибка указания имени рекомендации");
        }
        return recommendationType;
    }
}
