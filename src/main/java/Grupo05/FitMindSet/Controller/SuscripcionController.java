package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.Service.SuscripcionService;
import Grupo05.FitMindSet.dto.response.SuscripcionPagoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/suscripcion")
public class SuscripcionController {

    private final SuscripcionService suscripcionService;

    @GetMapping("/reporte/{usuarioId}")
    public ResponseEntity<List<SuscripcionPagoResponseDTO>> obtenerReportePagos(@PathVariable Long usuarioId) {
        List<SuscripcionPagoResponseDTO> pagos = suscripcionService.obtenerReportePagosPorUsuario(usuarioId);
        return ResponseEntity.ok(pagos);
    }
}
