package Grupo05.FitMindSet.dto.Request;

import Grupo05.FitMindSet.domain.Enum.Estado;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MetaDTO {
    private String descripcion;
    private String estado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Integer tiempoObjetivo;
}
