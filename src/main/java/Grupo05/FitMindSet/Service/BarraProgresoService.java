package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.dto.response.BarraProgresoResponseDTO;
import Grupo05.FitMindSet.domain.Entity.Meta;
import Grupo05.FitMindSet.domain.Entity.Seguimiento;
import Grupo05.FitMindSet.Repository.MetaRepository;
import Grupo05.FitMindSet.Repository.SeguimientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarraProgresoService {

    private final MetaRepository metaRepository;
    private final SeguimientoRepository seguimientoRepository;

    public BarraProgresoService(MetaRepository metaRepository, SeguimientoRepository seguimientoRepository) {
        this.metaRepository = metaRepository;
        this.seguimientoRepository = seguimientoRepository;
    }

    public BarraProgresoResponseDTO obtenerProgresoMeta(Long metaId) {
        Meta meta = metaRepository.findById(metaId).orElseThrow(() -> new RuntimeException("Meta no encontrada"));

        List<Seguimiento> seguimientos = seguimientoRepository.findByMeta(meta);

        int porcentajeTotal = seguimientos.stream().mapToInt(Seguimiento::getPorcentaje).sum();
        int porcentajeAvance = Math.min(porcentajeTotal / seguimientos.size(), 100); // Máximo 100%

        BarraProgresoResponseDTO response = new BarraProgresoResponseDTO();
        response.setMetaId(meta.getId());
        response.setDescripcionMeta(meta.getDescripcion());
        response.setPorcentajeAvance(porcentajeAvance);

        return response;
    }
}
