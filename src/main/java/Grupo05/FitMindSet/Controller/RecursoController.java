package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.dto.request.RecursoUpdateRequestDTO;
import Grupo05.FitMindSet.dto.response.RecursoResponseDTO;
import Grupo05.FitMindSet.Service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recursos")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    // Endpoint para actualizar un recurso
    @PutMapping("/{recursoId}")
    public ResponseEntity<RecursoResponseDTO> actualizarRecurso(@PathVariable Long recursoId,
                                                             @RequestBody RecursoUpdateRequestDTO request) {
        RecursoResponseDTO recursoActualizado = recursoService.actualizarRecurso(recursoId, request);
        return ResponseEntity.ok(recursoActualizado);
    }
}
