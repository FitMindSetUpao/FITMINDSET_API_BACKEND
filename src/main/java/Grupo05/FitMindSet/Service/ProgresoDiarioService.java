package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.Repository.SeguimientoRepository;
import Grupo05.FitMindSet.domain.Entity.Seguimiento;
import Grupo05.FitMindSet.dto.response.ProgresoDiarioResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProgresoDiarioService {

    private final SeguimientoRepository seguimientoRepository;

    public List<ProgresoDiarioResponseDTO> obtenerProgresoDiario(Long usuarioId) {
        List<Seguimiento> seguimientos = seguimientoRepository.findByMetaUsuarioId(usuarioId);

        return seguimientos.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    private ProgresoDiarioResponseDTO convertirADTO(Seguimiento seguimiento) {
        ProgresoDiarioResponseDTO dto = new ProgresoDiarioResponseDTO();
        dto.setMetaDescripcion(seguimiento.getMeta().getDescripcion());
        dto.setFecha(seguimiento.getFecha());
        dto.setPorcentajeCumplido(seguimiento.getPorcentaje());
        dto.setTiempoCumplido(seguimiento.getTiempoCumplido());
        dto.setEstado(seguimiento.getEstado().name());

        return dto;
    }
}