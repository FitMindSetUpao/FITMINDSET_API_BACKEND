package Grupo05.FitMindSet.dto;

import lombok.Data;

@Data
public class EmailDTO {
    private String destinatario;
    private String asunto;
    private String mensaje;
}