package pro.dev.animalshelter.interfaces;

import pro.dev.animalshelter.model.RecommendationInformation;

import java.util.Collection;

public interface RecommendationInformationInterface {
    RecommendationInformation createRecommendationInformation(String typeName, String information);

    RecommendationInformation updateRecommendationInformation(String typeName, String information);

    RecommendationInformation deleteRecommendationInformation(String typeName);

    RecommendationInformation getInformationBy(String typeName);

    Collection<RecommendationInformation> getAllRecommendationInformation();

    Collection<String> getRecommendationTypesNameList();
}
