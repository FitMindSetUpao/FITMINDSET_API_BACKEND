package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.dto.AuthResponseDTO;
import Grupo05.FitMindSet.dto.LoginDTO;
import Grupo05.FitMindSet.dto.UserProfileDTO;
import Grupo05.FitMindSet.dto.UserRegistrationDTO;
import Grupo05.FitMindSet.Exception.RoleNotFoundException;
import Grupo05.FitMindSet.dto.response.RegistroActividadResponseDTO;
import Grupo05.FitMindSet.dto.request.RegistroActividadRequestDTO;

public interface UsuarioService {
    // Métodos de gestión de usuarios
    UserProfileDTO registerCustomer(UserRegistrationDTO registrationDTO) throws RoleNotFoundException;

    UserProfileDTO registerAuthor(UserRegistrationDTO registrationDTO) throws RoleNotFoundException;

    AuthResponseDTO login(LoginDTO loginDTO);

    UserProfileDTO updateUserProfile(Long id, UserProfileDTO userProfileDTO);

    UserProfileDTO getUserProfileById(Long id);

    boolean isEmailRegistered(String correo);
    
    // Método de registro de actividad
    RegistroActividadResponseDTO registrarActividad(RegistroActividadRequestDTO request);

    // Métodos relacionados con la eliminación de usuario
    void delete(Long id);

    void eliminarUsuarioPorCorreo(String correo);
}
