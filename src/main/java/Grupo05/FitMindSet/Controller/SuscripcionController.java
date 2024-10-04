package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.dto.request.CancelarSuscripcionRequestDTO;
import Grupo05.FitMindSet.Service.SuscripcionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suscripcion")
public class SuscripcionController {

    private final SuscripcionService suscripcionService;

    public SuscripcionController(SuscripcionService suscripcionService) {
        this.suscripcionService = suscripcionService;
    }

    @PostMapping("/cancelar")
    public ResponseEntity<String> cancelarSuscripcion(@RequestBody CancelarSuscripcionRequestDTO request) {
        suscripcionService.cancelarSuscripcion(request.getUsuarioId());
        return ResponseEntity.ok("Suscripción cancelada exitosamente.");
    }
}