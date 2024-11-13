package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.Exception.ResourceNotFoundException;
import Grupo05.FitMindSet.Service.SuscripcionService;
import Grupo05.FitMindSet.dto.AccessResponse;
import Grupo05.FitMindSet.dto.SubscriptionCaptureResponse;
import Grupo05.FitMindSet.dto.SubscriptionResponse;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/suscripciones")
public class SuscripcionController {

    private final SuscripcionService suscripcionService;
    private static final Logger logger = LoggerFactory.getLogger(SuscripcionController.class);

    @PostMapping("/crear")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> crearSuscripcion(@RequestParam String paypalPlanId,
                                              @RequestParam String returnUrl,
                                              @RequestParam String cancelUrl) {
        if (paypalPlanId == null || returnUrl == null || cancelUrl == null ||
                paypalPlanId.isEmpty() || returnUrl.isEmpty() || cancelUrl.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Todos los parámetros son obligatorios.");
        }

        logger.info("Creando suscripción con paypalPlanId: {}, returnUrl: {}, cancelUrl: {}",
                paypalPlanId, returnUrl, cancelUrl);

        try {
            SubscriptionResponse response = suscripcionService.createSubscription(paypalPlanId, returnUrl, cancelUrl);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ResourceNotFoundException e) {
            logger.error("El plan con ID {} no fue encontrado.", paypalPlanId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: El plan con ID " + paypalPlanId + " no fue encontrado.");
        } catch (Exception e) {
            // Log detallado de errores genéricos
            logger.error("Error al crear la suscripción: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: Se produjo un error inesperado al crear la suscripciónC.");
        }
    }


    @PostMapping("/capturar/{subscriptionId}")
    public ResponseEntity<SubscriptionCaptureResponse> capturarSuscripcion(
            @PathVariable String subscriptionId) {
        try {
            SubscriptionCaptureResponse response = suscripcionService.captureSubscription(subscriptionId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}