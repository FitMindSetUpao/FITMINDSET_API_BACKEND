package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.Service.EmailService;
import Grupo05.FitMindSet.Service.UsuarioService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/notificaciones")
public class NotificationController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UsuarioService usuarioService;
    @PostMapping("/preferences")
    public ResponseEntity<?> setNotificationPreference(@RequestBody Map<String, String> request) {
        String preference = request.get("preference");
        String recipientEmail = request.get("email");
        if (usuarioService.isEmailRegistered(recipientEmail)) {
            try {
                emailService.sendReminderBasedOnPreference(recipientEmail, preference);
                return ResponseEntity.ok("Preferencia guardada y notificación enviada según preferencia.");
            } catch (MessagingException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar la notificación");
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Preferencia inválida");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El correo electrónico no está registrado.");
        }
    }
}
