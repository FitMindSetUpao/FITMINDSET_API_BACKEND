package Grupo05.FitMindSet.Mapper;

import Grupo05.FitMindSet.domain.Entity.Usuario;
import Grupo05.FitMindSet.dto.AuthResponseDTO;
import Grupo05.FitMindSet.dto.LoginDTO;
import Grupo05.FitMindSet.dto.UserProfileDTO;
import Grupo05.FitMindSet.dto.UserRegistrationDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class UserMapper {
    private final ModelMapper modelMapper;
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Usuario toUserEntity(UserRegistrationDTO registrationDTO) {
        return modelMapper.map(registrationDTO, Usuario.class);

    }
    public UserProfileDTO toUserProfileDTO(Usuario usuario) {
        UserProfileDTO userProfileDTO = modelMapper.map(usuario, UserProfileDTO.class);

        if(usuario.getCustomer() != null) {
            userProfileDTO.setNombre(usuario.getCustomer().getNombre());
            userProfileDTO.setApellidos(usuario.getCustomer().getApellidos());

        }
        if (usuario.getAutor() != null) {
            userProfileDTO.setNombre(usuario.getAutor().getNombre());
            userProfileDTO.setApellidos(usuario.getAutor().getApellidos());
            userProfileDTO.setEspecialidad(usuario.getAutor().getEspecialidad());
        }
        return userProfileDTO;
    }
    public Usuario toUserEntity(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, Usuario.class);
    }
    public AuthResponseDTO toAuthResponseDTO(Usuario usuario, String token) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);

        if(usuario.getCustomer() != null) {
            authResponseDTO.setNombre(usuario.getCustomer().getNombre());
            authResponseDTO.setApellidos(usuario.getCustomer().getApellidos());

        }
        else if(usuario.getAutor() != null) {
            authResponseDTO.setNombre(usuario.getAutor().getNombre());
            authResponseDTO.setApellidos(usuario.getAutor().getApellidos());

        }
        authResponseDTO.setRol(usuario.getRol().getName().toString());
        return authResponseDTO;
    }
}
