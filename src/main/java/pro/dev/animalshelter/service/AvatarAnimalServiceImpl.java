package pro.dev.animalshelter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.dev.animalshelter.exception.AvatarAnimalDefaultImageNotFound;
import pro.dev.animalshelter.model.AvatarAnimal;
import pro.dev.animalshelter.model.Animal;
import pro.dev.animalshelter.repository.AvatarAnimalRepository;
import pro.dev.animalshelter.repository.AnimalRepository;
import pro.dev.animalshelter.interfaces.AvatarAnimalService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarAnimalServiceImpl implements AvatarAnimalService {
    private final AnimalRepository animalRepository;
    private final AvatarAnimalRepository avatarAnimalRepository;
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;
    private final int BUFFER_SIZE = 1024;

    public AvatarAnimalServiceImpl(AnimalRepository animalRepository, AvatarAnimalRepository avatarAnimalRepository) {
        this.animalRepository = animalRepository;
        this.avatarAnimalRepository = avatarAnimalRepository;
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private AvatarAnimal findAvatarByAnimalId(Long animalId) {
        return
                avatarAnimalRepository.findById(animalId)
                        .orElse(new AvatarAnimal());
    }

    private Path saveToDisk(Animal animal, MultipartFile avatarFile) throws IOException {
        Path filePath = Path.of(avatarsDir, "animal" + animal.getIdAnimal() + "avatarAnimal." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, BUFFER_SIZE);
                BufferedOutputStream bos = new BufferedOutputStream(os, BUFFER_SIZE);
        ) {
            bis.transferTo(bos);
        }
        return filePath;
    }

    private void saveToDd(Animal animal, MultipartFile avatarFile, Path filePath) throws IOException {
        AvatarAnimal avatarAnimal = findAvatarByAnimalId(animal.getIdAnimal());
        avatarAnimal.setAnimal(animal);
        avatarAnimal.setFilePath(filePath.toString());
        avatarAnimal.setFileSize(avatarFile.getSize());
        avatarAnimal.setMediaType(avatarFile.getContentType());
        avatarAnimal.setData(avatarFile.getBytes());
        avatarAnimalRepository.save(avatarAnimal);
    }

    @Override
    public void uploadAvatarAnimal(Long animalId, MultipartFile avatarFile) throws IOException {
        Animal animal = animalRepository
                .findById(animalId)
                .orElseThrow();
        Path path = saveToDisk(animal, avatarFile);
        saveToDd(animal, avatarFile, path);
    }

    @Override
    public AvatarAnimal findAvatarAnimal(Long id) {
        return avatarAnimalRepository.findById(id).orElseThrow();
    }

    @Override
    public List<AvatarAnimal> getPaginatedAvatarAnimals(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarAnimalRepository.findAll(pageRequest).getContent();
    }

    @Override
    public AvatarAnimal findAvatarAnimalOrReturnDefaultAvailableImage(Animal animal) {
        Optional<AvatarAnimal> findAvatar = avatarAnimalRepository.findById(animal.getIdAnimal());

        if (findAvatar.isPresent()) {
            return findAvatar.get();
        } else {
            return getAvatarAnimalWithDefaultNoImageFoundImage();
        }
    }

    private AvatarAnimal getAvatarAnimalWithDefaultNoImageFoundImage() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("images/noImageFound.jpg");
        AvatarAnimal avatar = new AvatarAnimal();
        try {
            avatar.setData(is.readAllBytes());
        } catch (IOException e) {
            throw new AvatarAnimalDefaultImageNotFound();
        }
        return avatar;
    }
}
