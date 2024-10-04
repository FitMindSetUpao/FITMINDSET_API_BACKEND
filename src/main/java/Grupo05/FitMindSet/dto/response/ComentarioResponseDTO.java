package Grupo05.FitMindSet.dto.response;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComentarioResponseDTO {
    private Long comentarioId;
    private String contenido;
    private LocalDateTime fechaPublicacion;
    private String customerNombre;
}
