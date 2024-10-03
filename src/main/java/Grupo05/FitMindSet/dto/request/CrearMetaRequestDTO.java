package Grupo05.FitMindSet.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CrearMetaRequestDTO {
    private Long usuarioId;
    private String descripcionMeta;
    private LocalDateTime fechaFin;
}