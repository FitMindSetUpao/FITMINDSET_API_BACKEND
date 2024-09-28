package Grupo05.FitMindSet.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RegistroActividadRequestDTO {
    private Long usuarioId;
    private Long metaId;
    private Integer porcentajeCumplido;
    private Integer tiempoCumplido; // En minutos
    private LocalDateTime fecha;
}
