package Grupo05.FitMindSet.dto.request;

import Grupo05.FitMindSet.domain.Enum.TipoRecurso;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecursoUpdateRequestDTO {

    private String nombreRecurso;
    private String descripcionRecurso;
    private BigDecimal precioRecurso;
    private TipoRecurso tipoRecurso;

    // Si hay campos adicionales que pueden ser modificados, puedes agregarlos aquí
}
