package Grupo05.FitMindSet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AutorDTO {
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max= 50, message = "El nombre debe tener 50 caracteres o menos")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido debe tener 50 caracteres o menos")
    private String apellido;

    @NotBlank(message = "La especialidad es obligatorio")
    @Size(max = 35, message = "La especialidad debe tener 35 caracteres o menos")
    private String especialidad;
}
