package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.dto.response.BarraProgresoResponseDTO;
import Grupo05.FitMindSet.Service.BarraProgresoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/progreso")
public class BarraProgresoController {

    private final BarraProgresoService barraProgresoService;

    public BarraProgresoController(BarraProgresoService barraProgresoService) {
        this.barraProgresoService = barraProgresoService;
    }

    @GetMapping("/{metaId}")
    public ResponseEntity<BarraProgresoResponseDTO> obtenerProgreso(@PathVariable Long metaId) {
        BarraProgresoResponseDTO response = barraProgresoService.obtenerProgresoMeta(metaId);
        return ResponseEntity.ok(response);
    }
}
