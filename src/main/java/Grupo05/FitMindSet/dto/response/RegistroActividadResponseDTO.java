package Grupo05.FitMindSet.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RegistroActividadResponseDTO {
    private Long seguimientoId;
    private LocalDateTime fecha;
    private Integer porcentajeCumplido;
    private Integer tiempoCumplido;
    private String estado;
}