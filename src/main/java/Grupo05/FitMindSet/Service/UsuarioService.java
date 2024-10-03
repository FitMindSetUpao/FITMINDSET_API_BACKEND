package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.Mapper.UserMapper;
import Grupo05.FitMindSet.Repository.RolRepository;
import Grupo05.FitMindSet.Repository.UsuarioRepository;
import Grupo05.FitMindSet.Security.TokenProvider;
import Grupo05.FitMindSet.domain.Entity.Rol;
import Grupo05.FitMindSet.domain.Entity.Usuario;
import Grupo05.FitMindSet.domain.Enum.ERole;
import Grupo05.FitMindSet.dto.*;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Grupo05.FitMindSet.Exception.UserNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final RolRepository rolRepository;

    /**
     * Registrar un cliente.
     */
    @Transactional
    public UserProfileDTO registerCustomer(UserRegistrationDTO registrationDTO) {
        validateEmail(registrationDTO.getCorreo());

        Usuario usuario = userMapper.toUser(registrationDTO);
        usuario.setContrasena(passwordEncoder.encode(registrationDTO.getContrasena()));

        // Llamada corregida al método findByName
        Rol rolCliente = rolRepository.findByName(ERole.CUSTOMER)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        usuario.setRol(rolCliente);
        usuarioRepository.save(usuario);
        emailService.sendEmail(
                usuario.getCorreo(),
                "Bienvenido a la plataforma",
                "Hola " + usuario.getNombre() + ", gracias por registrarte en nuestra plataforma."
        );

        return userMapper.toUserProfileDTO(usuario);
    }

    /**
     * Registrar un autor.
     */
    /**
     * Registrar un autor.
     */
    @Transactional
    public UserProfileDTO registerAuthor(UserRegistrationDTO registrationDTO) {
        validateEmail(registrationDTO.getCorreo());
        Usuario usuario = userMapper.toUser(registrationDTO);
        usuario.setContrasena(passwordEncoder.encode(registrationDTO.getContrasena()));

        // Obtener el rol correspondiente
        Rol rolAutor = rolRepository.findByName(ERole.AUTHOR)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRol(rolAutor);

        usuarioRepository.save(usuario);

        emailService.sendEmail(
                usuario.getCorreo(),
                "Bienvenido a la plataforma",
                "Hola " + usuario.getNombre() + ", gracias por registrarte como autor."
        );

        return userMapper.toUserProfileDTO(usuario);
    }

    /**
     * Autenticar usuario (login).
     */
    @Transactional(readOnly = true)
    public AuthResponseDTO login(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getCorreo(),
                loginDTO.getContrasena()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.createAccessToken(authentication);
        UserProfileDTO userProfileDTO = getUserProfileById(((Usuario) authentication.getPrincipal()).getId());
        return userMapper.toAuthResponseDTO(accessToken, userProfileDTO);
    }

    /**
     * Actualizar el perfil de usuario.
     */
    @Transactional
    public UserProfileDTO updateUserProfile(Long id, UserProfileDTO userProfileDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado."));

        usuario.setNombre(userProfileDTO.getNombre());
        usuario.setCorreo(userProfileDTO.getCorreo());
        // Otras propiedades a actualizar

        usuarioRepository.save(usuario);
        return userMapper.toUserProfileDTO(usuario);
    }

    /**
     * Obtener el perfil de usuario por ID.
     */
    @Transactional(readOnly = true)
    public UserProfileDTO getUserProfileById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado."));
        return userMapper.toUserProfileDTO(usuario);
    }

    /**
     * Validar si el correo electrónico ya existe.
     */
    private void validateEmail(String email) {
        if (usuarioRepository.existsByCorreo(email)) {
            throw new BadRequestException("El correo electrónico ya está siendo usado por otro usuario.");
        }
    }

    /**
     * Método para restablecer la contraseña.
     */
    @Transactional
    public void resetPassword(String email, String newPassword) {
        Usuario usuario = usuarioRepository.findOneByCorreo(email)
                .orElseThrow(() -> new BadRequestException("Usuario no encontrado."));

        usuario.setContrasena(passwordEncoder.encode(newPassword));
        usuarioRepository.save(usuario);
    }

    /**
     * Eliminar el perfil del usuario autenticado.
     */
    @Transactional
    public void eliminarPerfil() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadRequestException("No hay usuario autenticado.");
        }

        String correoUsuario = authentication.getName();
        Usuario usuario = usuarioRepository.findOneByCorreo(correoUsuario)
                .orElseThrow(() -> new BadRequestException("Usuario no encontrado."));
        usuarioRepository.delete(usuario);
    }

    /**
     * Eliminar usuario por correo.
     */
    @Transactional
    public void eliminarUsuarioPorCorreo(String correo) {
        Usuario usuario = usuarioRepository.findOneByCorreo(correo)
                .orElseThrow(() -> new BadRequestException("Usuario no encontrado."));
        usuarioRepository.delete(usuario);
    }

    /**
     * Eliminar usuario por ID.
     */
    @Transactional
    public void delete(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Usuario no encontrado con el ID: " + id));
        usuarioRepository.delete(usuario);
    }

    /**
     * Actualizar preferencias de notificación.
     */
    public void updateNotificationPreferences(NotificationPreferencesDTO preferences) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findOneByCorreo(preferences.getEmail());

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNotificationFrequency(preferences.getFrequency());
            usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    /**
     * Registrar peso y altura.
     */
    public void registrarPesoAltura(Long id, Double peso, Double altura) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setPeso(peso);
        usuario.setAltura(altura);
        usuarioRepository.save(usuario);
    }

    /**
     * Actualizar peso y altura y calcular IMC.
     */
    public Double actualizarPesoAlturaYCalcularIMC(Long id, Double peso, Double altura) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setPeso(peso);
        usuario.setAltura(altura);
        usuarioRepository.save(usuario);
        return usuario.calcularIMC();
    }

    /**
     * Calcular IMC.
     */
    public Double calcularIMC(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario.calcularIMC();
    }

    /**
     * Actualizar peso y altura.
     */
    public void actualizarPesoAltura(Long id, Double nuevoPeso, Double nuevaAltura) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setPeso(nuevoPeso);
        usuario.setAltura(nuevaAltura);
        usuarioRepository.save(usuario);
    }
}
