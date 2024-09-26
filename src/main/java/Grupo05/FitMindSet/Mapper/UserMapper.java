package Grupo05.FitMindSet.Mapper;

import Grupo05.FitMindSet.domain.Entity.Rol;
import Grupo05.FitMindSet.domain.Entity.Usuario;
import Grupo05.FitMindSet.dto.AuthResponseDTO;
import Grupo05.FitMindSet.dto.UserProfileDTO;
import Grupo05.FitMindSet.dto.UserRegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "userProfile.rol", target = "rol")
    AuthResponseDTO toAuthResponseDTO(String token, UserProfileDTO userProfile);

    // Método para mapear Rol a String
    default String map(Rol rol) {
        return rol != null ? rol.name() : null;
    }

    // Mapea Usuario a UserProfileDTO
    UserProfileDTO toUserProfileDTO(Usuario usuario);

    // Método para mapear UserRegistrationDTO a Usuario
    Usuario toUser(UserRegistrationDTO userRegistrationDTO);
}
