package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.dto.request.CancelarSuscripcionRequestDTO;
import Grupo05.FitMindSet.Service.SuscripcionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suscripcion")
public class SuscripcionController {
    @GetMapping("/beneficios/{suscripcionId}")
    public ResponseEntity<AccessResponse> accederBeneficios(
            @PathVariable Long suscripcionId,
            Authentication authentication) {
        try {
            AccessResponse accessResponse = suscripcionService.accessSubscriptionBenefits(suscripcionId);
            return ResponseEntity.ok(accessResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/acceso-recursos/{suscripcionId}")
    public ResponseEntity<AccessResponse> getAccess(@PathVariable Long suscripcionId) {
        AccessResponse response = suscripcionService.accessSubscriptionBenefits(suscripcionId);
        return ResponseEntity.ok(response);
    }
}