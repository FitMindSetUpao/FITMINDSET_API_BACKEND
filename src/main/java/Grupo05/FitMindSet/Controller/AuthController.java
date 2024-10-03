package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.Service.UsuarioService;
import Grupo05.FitMindSet.domain.Entity.Usuario;
import Grupo05.FitMindSet.dto.AuthResponseDTO;
import Grupo05.FitMindSet.dto.LoginDTO;
import Grupo05.FitMindSet.dto.UserProfileDTO;
import Grupo05.FitMindSet.dto.UserRegistrationDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService userService;

    @PostMapping("/register/customer")
    public ResponseEntity<UserProfileDTO> registerCustomer(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserProfileDTO userProfile = userService.registerCustomer(userRegistrationDTO);
        return new ResponseEntity<>(userProfile, HttpStatus.CREATED);
    }

    @PostMapping("/register/author")
    public ResponseEntity<UserProfileDTO> registerAuthor(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserProfileDTO userProfile = userService.registerAuthor(userRegistrationDTO);
        return new ResponseEntity<>(userProfile, HttpStatus.CREATED);
    }

    // Endpoint para el login
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        AuthResponseDTO authResponse = userService.login(loginDTO);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
    @GetMapping("/google/callback")
    public ResponseEntity<?> googleCallback(@RequestParam("code") String code) {
        String accessToken = exchangeCodeForAccessToken(code);

        OAuth2User user = getUserFromGoogle(accessToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getAttribute("email"));

        if (userDetails != null) {
            String jwtToken = tokenProvider.createAccessToken(
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
            );
            return ResponseEntity.ok(jwtToken);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El correo no está registrado.");
        }
    }

    private String exchangeCodeForAccessToken(String code) {
        String tokenEndpoint = "https://oauth2.googleapis.com/token";
        RestTemplate restTemplate = new RestTemplate();

        String requestBody = "code=" + code +
                "&client_id=TU_CLIENT_ID" +
                "&client_secret=TU_CLIENT_SECRET" +
                "&redirect_uri=http://localhost:8080/auth/google/callback" +
                "&grant_type=authorization_code";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.exchange(tokenEndpoint, HttpMethod.POST, request, Map.class);
        return (String) response.getBody().get("access_token");
    }

    private OAuth2User getUserFromGoogle(String accessToken) {
        String userInfoEndpoint = "https://www.googleapis.com/oauth2/v2/userinfo";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);  // Establecer el token de acceso en el encabezado

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpoint, HttpMethod.GET, request, Map.class);

        // Aquí puedes construir un OAuth2User a partir de la respuesta
        Map<String, Object> userAttributes = response.getBody();
        return new DefaultOAuth2User(
                Collections.singletonList(new OAuth2UserAuthority(userAttributes)),
                userAttributes,
                "id");  // o el atributo que desees usar como principal
    }

    @GetMapping("/login")
    public String login() {

        return "Redirigiendo a la autenticación de Google...";
    }

    @GetMapping("/login/oauth2/code/google")
    public String googleLogin(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");

        if (userServiceImpl.isEmailRegistered(email)) {

            return "redirect:/home";
        } else {
            return "redirect:/login?error=not-registered";
        }
    }
}
