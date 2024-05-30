package pro.dev.animalshelter.conroller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.dev.animalshelter.dto.AvatarAnimalDTO;
import pro.dev.animalshelter.mapper.AvatarAnimalMapper;
import pro.dev.animalshelter.model.AvatarAnimal;
import pro.dev.animalshelter.interfaces.AvatarAnimalService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("avatars")
public class AvatarAnimalController {
    private final AvatarAnimalService avatarAnimalService;
    private final AvatarAnimalMapper avatarAnimalMapper;

    public AvatarAnimalController(AvatarAnimalService avatarAnimalService, AvatarAnimalMapper avatarAnimalMapper) {
        this.avatarAnimalService = avatarAnimalService;
        this.avatarAnimalMapper = avatarAnimalMapper;
    }

    @GetMapping
    public List<AvatarAnimalDTO> getPaginatedAvatars(
            @RequestParam int pageNumber,
            @RequestParam int pageSize
    ) {
        return avatarAnimalService.getPaginatedAvatarAnimals(pageNumber,pageSize)
                .stream()
                .map(avatarAnimalMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}/avatar-from-db")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        AvatarAnimal avatar = avatarAnimalService.findAvatarAnimal(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(avatar.getData());
    }

    @GetMapping(value = "/{id}/avatar-from-file")
    public void downloadAvatarAnimal(@PathVariable Long id, HttpServletResponse response) throws IOException {
        AvatarAnimal avatarAnimal = avatarAnimalService.findAvatarAnimal(id);

        Path path = Path.of(avatarAnimal.getFilePath());
        try(InputStream is = Files.newInputStream(path);
            OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatarAnimal.getMediaType());
            response.setContentLength((int) avatarAnimal.getFileSize());
            is.transferTo(os);
        }
    }
}