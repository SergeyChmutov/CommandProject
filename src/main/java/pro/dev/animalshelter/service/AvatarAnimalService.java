package pro.dev.animalshelter.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pro.dev.animalshelter.model.AvatarAnimal;


import java.io.IOException;
import java.util.List;

public interface AvatarAnimalService {
    void uploadAvatarAnimal ( Long avatarId, MultipartFile avatarAnimal) throws IOException;

    AvatarAnimal findAvatarAnimal(Long id);

    List<AvatarAnimal> getPaginatedAvatarAnimals (int pageNumber, int pageSize);
}
