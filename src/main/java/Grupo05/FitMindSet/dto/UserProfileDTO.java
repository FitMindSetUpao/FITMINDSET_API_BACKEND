package Grupo05.FitMindSet.dto;

import Grupo05.FitMindSet.domain.Enum.ERole;
import lombok.Data;

@Data
public class UserProfileDTO {
    private Long id;
    private String correo;
    private ERole role;

    private String nombre;
    private String apellidos;
    private String especialidad; //autor
    private String rol;
}
