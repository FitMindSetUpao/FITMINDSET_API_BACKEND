package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.Service.ProgresoDiarioService;
import Grupo05.FitMindSet.dto.response.ProgresoDiarioResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/progreso")
public class ProgresoDiarioController {

    private final ProgresoDiarioService progresoDiarioService;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<ProgresoDiarioResponseDTO>> obtenerProgresoDiario(@PathVariable Long usuarioId) {
        List<ProgresoDiarioResponseDTO> progreso = progresoDiarioService.obtenerProgresoDiario(usuarioId);
        return ResponseEntity.ok(progreso);
    }
}