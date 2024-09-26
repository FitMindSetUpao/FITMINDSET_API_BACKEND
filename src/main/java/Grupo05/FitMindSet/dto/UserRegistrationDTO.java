package Grupo05.FitMindSet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRegistrationDTO {
        @NotEmpty(message ="El nombre es obligatorio")
        private String nombre;
        @NotEmpty(message = "Los apellidos son obligatorios")
        private String apellidos;
        @Email(message = "Debe proporcionar un correo válido")
        @NotEmpty(message = "El correo es obligatorio")
        private String correo;

        @NotEmpty(message = "La contraseña es obligatoria")
        private String contrasena;

        private int edad;
        private String genero;
        private String especialidad;//en casos del autor

    }

