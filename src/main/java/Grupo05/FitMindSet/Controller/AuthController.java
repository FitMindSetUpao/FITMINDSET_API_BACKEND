package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.Service.UsuarioService;
import Grupo05.FitMindSet.domain.Entity.Usuario;
import Grupo05.FitMindSet.dto.AuthResponseDTO;
import Grupo05.FitMindSet.dto.LoginDTO;
import Grupo05.FitMindSet.dto.UserProfileDTO;
import Grupo05.FitMindSet.dto.UserRegistrationDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register/customer")
    public ResponseEntity<UserProfileDTO> registerCustomer(@RequestBody @Valid UserRegistrationDTO registrationDTO) {
        UserProfileDTO profile = usuarioService.registerCustomer(registrationDTO);
        return ResponseEntity.ok(profile);
    }

    // Endpoint para registrar un autor
    @PostMapping("/register/author")
    public ResponseEntity<UserProfileDTO> registerAuthor(@RequestBody @Valid UserRegistrationDTO registrationDTO) {
        UserProfileDTO profile = usuarioService.registerAuthor(registrationDTO);
        return ResponseEntity.ok(profile);
    }

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        AuthResponseDTO authResponse = usuarioService.login(loginDTO);
        return ResponseEntity.ok(authResponse);
    }

    // Endpoint para obtener el perfil de un usuario (autenticado)
    @GetMapping("/profile/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'AUTHOR')")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable Long id) {
        UserProfileDTO profile = usuarioService.getUserProfileById(id);
        return ResponseEntity.ok(profile);
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String correo = request.get("correo");
        if (correo == null || correo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo no puede estar vacío.");
        }
        System.out.println("Verificando si el correo está registrado: " + correo);
        if (!usuarioService.isCorreoRegistered(correo)) {
            System.out.println("No se encontró un usuario con el correo: " + correo);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró un usuario con este correo.");
        }
        String nombreUsuario = usuarioService.getUserNameByEmail(correo); // Cambiado para utilizar el servicio
        String token = tokenProvider.createPasswordResetToken(correo);
        String resetLink = "http://localhost:4200/auth/reset-password?token=" + token;

        // Intentar enviar el correo electrónico
        try {
            emailService.sendPasswordResetEmail(correo, resetLink, nombreUsuario);
        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar el enlace de recuperación. Inténtalo de nuevo más tarde.");
        }

        return ResponseEntity.ok("Enlace de recuperación enviado al correo.");
    }
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("newPassword");

        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El token es inválido o ha expirado.");
        }
        if (!tokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido.");
        }
        String email = tokenProvider.getEmailFromToken(token);
        usuarioService.resetPassword(email, newPassword);
        return ResponseEntity.ok("Contraseña restablecida con éxito.");
    }
}
