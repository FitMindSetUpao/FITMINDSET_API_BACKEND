package Grupo05.FitMindSet.Service.impl;

import Grupo05.FitMindSet.Exception.BadRequestException;
import Grupo05.FitMindSet.Exception.InvalidCredentialsException;
import Grupo05.FitMindSet.Exception.ResourceNotFoundException;
import Grupo05.FitMindSet.Exception.RoleNotFoundException;
import Grupo05.FitMindSet.Mapper.UserMapper;
import Grupo05.FitMindSet.Repository.AutorRepository;
import Grupo05.FitMindSet.Repository.CustomerRepository;
import Grupo05.FitMindSet.Repository.RolRepository;
import Grupo05.FitMindSet.Repository.UsuarioRepository;
import Grupo05.FitMindSet.Security.TokenProvider;
import Grupo05.FitMindSet.Service.UsuarioService;
import Grupo05.FitMindSet.domain.Entity.Autor;
import Grupo05.FitMindSet.domain.Entity.Customer;
import Grupo05.FitMindSet.domain.Entity.Rol;
import Grupo05.FitMindSet.domain.Entity.Usuario;
import Grupo05.FitMindSet.domain.Enum.ERole;
import Grupo05.FitMindSet.dto.AuthResponseDTO;
import Grupo05.FitMindSet.dto.LoginDTO;
import Grupo05.FitMindSet.dto.UserProfileDTO;
import Grupo05.FitMindSet.dto.UserRegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CustomerRepository customerRepository;
    private final AutorRepository autorRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    @Autowired
    private MetaRepository metaRepository;

    public RecompensaResponseDTO crearMetaYGenerarRecompensa(CrearMetaRequestDTO request) {
        // Se asume que ya tienes el usuario cargado
        Meta meta = new Meta();
        meta.setDescripcion(request.getDescripcionMeta());
        meta.setFechaInicio(LocalDateTime.now());
        meta.setFechaFin(request.getFechaFin());
        meta.setEstado("Pendiente");
        metaRepository.save(meta);

        RecompensaResponseDTO response = new RecompensaResponseDTO();
        response.setMetaId(meta.getId());
        response.setEstadoMeta("Meta creada exitosamente");

        // Lógica para recompensas
        if (metaCumplida(meta)) {
            response.setRecompensa("Has recibido una medalla por cumplir tu meta.");
        } else {
            response.setRecompensa("Aún no has cumplido tu meta. Sigue adelante.");
        }

        return response;
    }

    private boolean metaCumplida(Meta meta) {
        // Validación simple para comprobar si la meta fue cumplida
        return LocalDateTime.now().isAfter(meta.getFechaFin());
    }
}
