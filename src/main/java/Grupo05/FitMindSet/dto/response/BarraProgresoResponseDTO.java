package Grupo05.FitMindSet.dto.response;

public class BarraProgresoResponseDTO {
    private Long metaId;
    private String descripcionMeta;
    private int porcentajeAvance; // De 0 a 100%

    // Getters y Setters
    public Long getMetaId() {
        return metaId;
    }

    public void setMetaId(Long metaId) {
        this.metaId = metaId;
    }

    public String getDescripcionMeta() {
        return descripcionMeta;
    }

    public void setDescripcionMeta(String descripcionMeta) {
        this.descripcionMeta = descripcionMeta;
    }

    public int getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(int porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }
}
