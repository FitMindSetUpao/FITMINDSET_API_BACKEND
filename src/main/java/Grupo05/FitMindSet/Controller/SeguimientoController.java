package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.dto.response.RecompensaResponseDTO;
import Grupo05.FitMindSet.Service.UsuarioService;
import Grupo05.FitMindSet.dto.request.CrearMetaRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seguimiento")
public class SeguimientoController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crearMeta")
    public ResponseEntity<RecompensaResponseDTO> crearMeta(@RequestBody CrearMetaRequestDTO request) {
        RecompensaResponseDTO response = usuarioService.crearMetaYGenerarRecompensa(request);
        return ResponseEntity.ok(response);
    }
}
