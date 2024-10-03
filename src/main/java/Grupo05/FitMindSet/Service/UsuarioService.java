package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.Exception.RoleNotFoundException;
import Grupo05.FitMindSet.dto.AuthResponseDTO;
import Grupo05.FitMindSet.dto.LoginDTO;
import Grupo05.FitMindSet.dto.UserProfileDTO;
import Grupo05.FitMindSet.dto.UserRegistrationDTO;

public interface UsuarioService {
    UserProfileDTO registerCustomer(UserRegistrationDTO registrationDTO) throws RoleNotFoundException;

    UserProfileDTO registerAuthor(UserRegistrationDTO registrationDTO) throws RoleNotFoundException;

    AuthResponseDTO login(LoginDTO loginDTO);

    UserProfileDTO updateUserProfile(Long id, UserProfileDTO userProfileDTO);

    UserProfileDTO getUserProfileById(Long id);

    boolean isEmailRegistered(String correo);
    
    RegistroActividadResponseDTO registrarActividad(RegistroActividadRequestDTO request); // Método agregado a la interfaz
}
