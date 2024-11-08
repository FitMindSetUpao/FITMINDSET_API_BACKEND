package Grupo05.FitMindSet.dto.Response;

import Grupo05.FitMindSet.domain.Enum.Estado;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class MetaResponseDTO {
    private Long id;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Long habitoId;
    private Integer tiempoObjetivo;
}
