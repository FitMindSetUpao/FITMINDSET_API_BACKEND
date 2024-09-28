
package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.domain.Entity.Usuario;
import Grupo05.FitMindSet.domain.Entity.Seguimiento;
import Grupo05.FitMindSet.domain.Entity.Meta;
import Grupo05.FitMindSet.Repository.UsuarioRepository;
import Grupo05.FitMindSet.Repository.SeguimientoRepository;
import Grupo05.FitMindSet.domain.Enum.Estado;
import Grupo05.FitMindSet.Repository.MetaRepository;
import Grupo05.FitMindSet.dto.response.RegistroActividadResponseDTO;
import Grupo05.FitMindSet.dto.request.RegistroActividadRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class    UsuarioService {

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Autowired
    private MetaRepository metaRepository;

    public RegistroActividadResponseDTO registrarActividad(RegistroActividadRequestDTO request) {
        Meta meta = metaRepository.findById(request.getMetaId())
                .orElseThrow(() -> new RuntimeException("Meta no encontrada"));

        Seguimiento seguimiento = new Seguimiento();
        seguimiento.setMeta(meta);
        seguimiento.setFecha(request.getFecha());
        seguimiento.setPorcentaje(request.getPorcentajeCumplido());
        seguimiento.setTiempoCumplido(request.getTiempoCumplido());
        seguimiento.setEstado(Estado.ENPROCESO); // Estado Enum

        seguimientoRepository.save(seguimiento);

        RegistroActividadResponseDTO response = new RegistroActividadResponseDTO();
        response.setSeguimientoId(seguimiento.getId());
        response.setFecha(seguimiento.getFecha());
        response.setPorcentajeCumplido(seguimiento.getPorcentaje());
        response.setTiempoCumplido(seguimiento.getTiempoCumplido());
        response.setEstado("Actividad registrada correctamente");

        return response;
    }
}

