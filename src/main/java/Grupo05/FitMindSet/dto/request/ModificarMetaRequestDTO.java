package Grupo05.FitMindSet.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ModificarMetaRequestDTO {
    private Long metaId;
    private String nuevaDescripcion;
    private LocalDateTime nuevaFechaFin;
}
