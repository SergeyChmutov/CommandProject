package pro.dev.animalshelter.mapper;

import org.springframework.stereotype.Component;
import pro.dev.animalshelter.model.AvatarAnimal;
import pro.dev.animalshelter.dto.AvatarAnimalDTO;

@Component
public class AvatarAnimalMapper {
    public AvatarAnimalDTO mapToDTO (AvatarAnimal avatarAnimal) {
        AvatarAnimalDTO avatarAnimalDTO = new AvatarAnimalDTO();
        avatarAnimalDTO.setId(avatarAnimal.getId());
        avatarAnimalDTO.setMediaType(avatarAnimal.getMediaType());
        avatarAnimalDTO.setFileSize(avatarAnimal.getFileSize());
        avatarAnimalDTO.setAnimalId(avatarAnimal.getAnimal().getIdAnimal());

        return avatarAnimalDTO;
    }
}
