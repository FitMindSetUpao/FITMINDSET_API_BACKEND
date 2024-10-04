package Grupo05.FitMindSet.dto.response;

import Grupo05.FitMindSet.domain.Enum.TipoRecurso;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecursoResponseDTO {

    private Long recursoid;
    private String nombreRecurso;
    private String descripcionRecurso;
    private BigDecimal precioRecurso;
    private TipoRecurso tipoRecurso;
}
