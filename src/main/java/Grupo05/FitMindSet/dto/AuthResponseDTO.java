package Grupo05.FitMindSet.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;
    private String nombre;
    private String apellidos;
    private String rol;
    private String correo;
}