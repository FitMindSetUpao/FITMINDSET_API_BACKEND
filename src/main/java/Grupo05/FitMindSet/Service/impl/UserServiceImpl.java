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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CustomerRepository customerRepository;
    private final AutorRepository autorRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Transactional
    @Override
    public UserProfileDTO registerCustomer(UserRegistrationDTO registrationDTO) {
        return registerUserWithRole(registrationDTO, ERole.CUSTOMER);
    }

    @Transactional
    @Override
    public UserProfileDTO registerAuthor(UserRegistrationDTO registrationDTO) {
        return registerUserWithRole(registrationDTO, ERole.AUTHOR);
    }

    @Transactional
    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByCorreo(loginDTO.getCorreo())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email " + loginDTO.getCorreo()));

        if (!passwordEncoder.matches(loginDTO.getContrasena(), usuario.getContrasena())) {
            throw new InvalidCredentialsException("Credenciales incorrectas");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getCorreo(), loginDTO.getContrasena())
        );

        String token = tokenProvider.createAccessToken(authentication);
        AuthResponseDTO response = userMapper.toAuthResponseDTO(usuario, token);
        return response;
    }

    public UserProfileDTO registerUserWithRole(UserRegistrationDTO registrationDTO, ERole roleEnum) {
        boolean emailExists = usuarioRepository.existsByCorreo(registrationDTO.getCorreo());
        boolean existsAsCustomer = customerRepository.existsByNombreAndApellidos(registrationDTO.getNombre(), registrationDTO.getApellidos());
        boolean existsAsAutor = autorRepository.existsByNombreAndApellidos(registrationDTO.getNombre(), registrationDTO.getApellidos());

        if (emailExists) {
            throw new UsernameNotFoundException("El correo electrónico ya está registrado");
        }
        if (existsAsCustomer || existsAsAutor) {
            throw new BadRequestException("Ya existe un usuario con el mismo nombre y apellido");
        }

        Rol role = rolRepository.findByName(roleEnum)
                .orElseThrow(() -> new RoleNotFoundException("Rol no encontrado"));

        registrationDTO.setContrasena(passwordEncoder.encode(registrationDTO.getContrasena()));
        Usuario usuario = userMapper.toUserEntity(registrationDTO);
        usuario.setRol(role);

        if (roleEnum == ERole.CUSTOMER) {
            Customer customer = new Customer();
            customer.setNombre(registrationDTO.getNombre());
            customer.setApellidos(registrationDTO.getApellidos());
            customer.setEdad(registrationDTO.getEdad());
            customer.setGenero(registrationDTO.getGenero());
            customer.setUsuario(usuario); // Aquí vinculas el Customer con el Usuario.


            customer = customerRepository.save(customer);
            usuario.setCustomer(customer); // Asegúrate de que el Customer se asigne al Usuario.
        } else if (roleEnum == ERole.AUTHOR) {
            Autor autor = new Autor();
            autor.setNombre(registrationDTO.getNombre());
            autor.setApellidos(registrationDTO.getApellidos());
            autor.setEdad(registrationDTO.getEdad());
            autor.setGenero(registrationDTO.getGenero());
            autor.setEspecialidad(registrationDTO.getEspecialidad());
            autor.setUsuario(usuario);

            // Guardar el Autor primero.
            autor = autorRepository.save(autor);
            usuario.setAutor(autor);
        }

        Usuario savedUser = usuarioRepository.save(usuario);
        return userMapper.toUserProfileDTO(savedUser);
    }


    @Transactional
    @Override
    public UserProfileDTO updateUserProfile(Long id, UserProfileDTO userProfileDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        boolean existsAsCustomer = customerRepository.existsByNombreAndApellidosAndIdNot(userProfileDTO.getNombre(), userProfileDTO.getApellidos(), id);
        boolean existsAsAuthor = autorRepository.existsByNombreAndApellidosAndIdNot(userProfileDTO.getNombre(), userProfileDTO.getApellidos(), id);

        if (existsAsCustomer || existsAsAuthor) {
            throw new BadRequestException("Ya existe un usuario con el mismo nombre y apellido");
        }
        if (usuario.getCustomer() != null) {
            usuario.getCustomer().setNombre(userProfileDTO.getNombre());
            usuario.getCustomer().setApellidos(userProfileDTO.getApellidos());
        }
        if (usuario.getAutor() != null) {
            usuario.getAutor().setNombre(userProfileDTO.getNombre());
            usuario.getAutor().setApellidos(userProfileDTO.getApellidos());
            usuario.getAutor().setEspecialidad(userProfileDTO.getEspecialidad());
        }

        Usuario updatedUser = usuarioRepository.save(usuario);
        return userMapper.toUserProfileDTO(updatedUser);
    }

    @Transactional
    @Override
    public UserProfileDTO getUserProfileById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return userMapper.toUserProfileDTO(usuario);
    }
}
