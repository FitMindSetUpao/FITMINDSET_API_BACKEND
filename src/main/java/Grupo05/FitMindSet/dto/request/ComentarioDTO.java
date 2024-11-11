package Grupo05.FitMindSet.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ComentarioDTO {
    @NotBlank(message = "El contenido no puede estar vacío.")
    private String contenido;

    private Long grupoId;
    private Long customerId;
}
