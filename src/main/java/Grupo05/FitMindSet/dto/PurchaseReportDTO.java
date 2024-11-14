package Grupo05.FitMindSet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseReportDTO {
    private Integer cantidad;
    private String consultDate;
}
