package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.Service.RecursoService;
import Grupo05.FitMindSet.Service.StorageService;
import Grupo05.FitMindSet.dto.response.UploadMediaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@RequiredArgsConstructor
@RequestMapping("/media")
@RestController
public class MediaController {

    private final StorageService storageService;
    private final RecursoService recursoService;

    @PostMapping("/upload")
    public UploadMediaDTO upload(@RequestParam("file") MultipartFile multipartFile) {
        String path = storageService.store(multipartFile);
        return new UploadMediaDTO(path);
    }

    @GetMapping("/descargar/{recursoId}")
    public ResponseEntity<Resource> descargarRecurso(@PathVariable Long recursoId) throws IOException {
        String filename = recursoService.obtenerNombreArchivoPorRecursoId(recursoId);

        Resource resource = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(resource.getFile().toPath());

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }
}