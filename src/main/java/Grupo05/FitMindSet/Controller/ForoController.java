package Grupo05.FitMindSet.Controller;
import Grupo05.FitMindSet.dto.request.ComentarioRequestDTO;
import Grupo05.FitMindSet.dto.response.ComentarioResponseDTO;
import Grupo05.FitMindSet.dto.GrupoRequestDTO;
import Grupo05.FitMindSet.dto.GrupoResponseDTO;
import Grupo05.FitMindSet.Service.ComentarioService;
import Grupo05.FitMindSet.Service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/foro")
public class ForoController {


    @Autowired
    private GrupoService grupoService;

    @Autowired
    private ComentarioService comentarioService;

    // Obtener todos los grupos
    @GetMapping("/grupos")
    public ResponseEntity<List<GrupoResponseDTO>> getAllGrupos() {
        return ResponseEntity.ok(grupoService.getAllGrupos());
    }

    // Crear un nuevo grupo
    @PostMapping("/grupos")
    public ResponseEntity<GrupoResponseDTO> createGrupo(@RequestBody GrupoRequestDTO grupoRequestDTO) {
        return ResponseEntity.ok(grupoService.createGrupo(grupoRequestDTO));
    }

    // Obtener comentarios de un grupo específico
    @GetMapping("/grupos/{grupoId}/comentarios")
    public ResponseEntity<List<ComentarioResponseDTO>> getComentariosByGrupo(@PathVariable Long grupoId) {
        return ResponseEntity.ok(comentarioService.getComentariosByGrupo(grupoId));
    }

    // Crear un nuevo comentario en un grupo
    @PostMapping("/grupos/{grupoId}/comentarios")
    public ResponseEntity<ComentarioResponseDTO> createComentario(@PathVariable Long grupoId, @RequestBody ComentarioRequestDTO comentarioRequestDTO) {
        // Supongamos que tenemos el ID del usuario autenticado como "customerId"
        Long customerId = 1L; // Esto deberías obtenerlo del contexto de seguridad en un proyecto real
        return ResponseEntity.ok(comentarioService.createComentario(comentarioRequestDTO, customerId));
    }
}
