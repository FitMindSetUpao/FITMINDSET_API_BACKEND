package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.Repository.SuscripcionRepository;
import Grupo05.FitMindSet.domain.Entity.Suscripcion;
import Grupo05.FitMindSet.dto.response.SuscripcionPagoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SuscripcionService {
    private final SuscripcionRepository suscripcionRepository;
    public List<SuscripcionPagoResponseDTO> obtenerReportePagosPorUsuario(Long usuarioId) {
        List<Suscripcion> suscripciones = suscripcionRepository.findByUsuarioId(usuarioId);

        return suscripciones.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    private SuscripcionPagoResponseDTO convertirADTO(Suscripcion suscripcion) {
        SuscripcionPagoResponseDTO dto = new SuscripcionPagoResponseDTO();
        dto.setId(suscripcion.getId());
        dto.setMonto(suscripcion.getMonto());
        dto.setFechainicio(suscripcion.getFechainicio());
        dto.setFechafin(suscripcion.getFechafin());
        dto.setTipoSuscripcion(suscripcion.getTipoSuscripcion().name());
        dto.setNombrePlan(suscripcion.getPlan().getNombrePlan());
        return dto;
    }
}
