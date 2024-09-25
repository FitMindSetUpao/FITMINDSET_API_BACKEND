package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;

    @PostMapping("/register/customer")
    public ResponseEntity<UserProfileDTO> registerCustomer(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO){
        UserProfileDTO userProfile = usuarioService.registerAuthor(userRegistrationDTO);
        return new ResponseEntity<>(userProfile, HttpStatus.CREATED);
    }
    @PostMapping("/register/author")
    public ResponseEntity<UserProfileDTO> registerAuthor(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO){
        UserProfileDTO userProfile = usuarioService.registerAuthor(userRegistrationDTO);
        return new ResponseEntity<>(userProfile,HttpStatus.CREATED);

        @PostMapping("/login")
                public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO){
                AuthResponseDTO authResponseDTO = usuarioService.login(loginDTO);
                return new ResponseEntity<>(authResponse, HttpStatus.OK);

        }
    }

}
