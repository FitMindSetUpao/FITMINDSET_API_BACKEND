package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.dto.request.RecursoDTO;
import Grupo05.FitMindSet.Service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    // Obtener todos los recursos
    @GetMapping("/recursos")
    public ResponseEntity<List<RecursoDTO>> obtenerRecursos() {
        List<RecursoDTO> recursos = recursoService.obtenerTodosLosRecursos();
        if (recursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recursos);
    }

    // Buscar recursos por consulta
    @GetMapping("/recursos/buscar")
    public ResponseEntity<List<RecursoDTO>> buscarRecursos(@RequestParam String query) {
        List<RecursoDTO> recursos = recursoService.buscarRecursos(query);
        if (recursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recursos);
    }

    // Reportar recursos por autor
    @GetMapping("/reportar")
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<List<RecursoDTO>> reportarRecursos(Authentication authentication) {
        Long autorId = extractAutorId(authentication);

        if (autorId == null) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            List<RecursoDTO> recursos = recursoService.obtenerRecursosPorAutor(autorId);
            return ResponseEntity.ok(recursos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método auxiliar para mapear un Recurso a RecursoDTO
    private RecursoDTO mapToDTO(Recurso recurso) {
        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setNombreRecurso(recurso.getNombreRecurso());
        recursoDTO.setDescripcionRecurso(recurso.getDescripcionRecurso());
        recursoDTO.setPrecioRecurso(recurso.getPrecioRecurso());
        recursoDTO.setTipoRecurso(recurso.getTipoRecurso().name());
        return recursoDTO;
    }

    // Método auxiliar para extraer el ID del autor desde la autenticación
    private Long extractAutorId(Authentication authentication) {
        // Lógica para extraer el ID del autor desde el objeto de autenticación
        return null; // Implementar esta lógica según el sistema
    }
}
