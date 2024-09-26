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
}
