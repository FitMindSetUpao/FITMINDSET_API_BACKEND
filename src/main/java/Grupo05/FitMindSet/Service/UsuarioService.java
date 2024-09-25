
package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.domain.Entity.Usuario;
import Grupo05.FitMindSet.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    UserProfileDTO registerCustomer(UserRegistrationDTO registrationDTO);

    UserProfileDTO registerAuthor(UserRegistrationDTO registrationDTO);

    AuthResponseDTO login(LoginDTO loginDTO);

    UserProfileDTO getUserProfileById(Long id);
}


}

