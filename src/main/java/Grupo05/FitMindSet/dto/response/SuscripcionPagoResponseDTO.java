package Grupo05.FitMindSet.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SuscripcionPagoResponseDTO {
    private Long id;
    private BigDecimal monto;
    private LocalDateTime fechainicio;
    private LocalDateTime fechafin;
    private String tipoSuscripcion;
    private String nombrePlan;
}