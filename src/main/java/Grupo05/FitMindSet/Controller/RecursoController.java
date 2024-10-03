package Grupo05.FitMindSet.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

public class RecursoController {
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


    private RecursoDTO mapToDTO(Recurso recurso) {
        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setNombreRecurso(recurso.getNombreRecurso());
        recursoDTO.setDescripcionRecurso(recurso.getDescripcionRecurso());
        recursoDTO.setPrecioRecurso(recurso.getPrecioRecurso());
        recursoDTO.setTipoRecurso(recurso.getTipoRecurso().name());
        return recursoDTO;
    }
}

}
