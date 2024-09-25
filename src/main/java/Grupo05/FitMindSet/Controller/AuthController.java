package Grupo05.FitMindSet.Controller;

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
    private final UserService userService;

    @PostMapping("/register/customer")
    public ResponseEntity<UserProfileDTO> registerCustomer(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO){
        UserProfileDTO userProfile = userService.registerAuthor(userRegistrationDTO);
        return new ResponseEntity<>(userProfile, HttpStatus.CREATED);
    }
    @PostMapping("/register/author")
    public ResponseEntity<UserProfileDTO> registerAuthor(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO){
        UserProfileDTO userProfile = userService.registerAuthor(userRegistrationDTO);
        return new ResponseEntity<>(userProfile,HttpStatus.CREATED);

        @PostMapping("/login")
                public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO){
                AuthResponseDTO authResponseDTO = userService.login(loginDTO);
                return new ResponseEntity<>(authResponse, HttpStatus.OK)

        }
    }

}
