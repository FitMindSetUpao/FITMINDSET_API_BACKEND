package Grupo05.FitMindSet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @Email(message = "El correo electrónico no es válido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    private String correo;
    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasena;
}
