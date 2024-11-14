package Grupo05.FitMindSet.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SuscripcionDTO {
    private Long id;
    private Long customerId;
    private String paypalPlanId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private BigDecimal monto;
    private String orderId;
}