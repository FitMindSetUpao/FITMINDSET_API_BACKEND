package Grupo05.FitMindSet.Mapper;

import Grupo05.FitMindSet.domain.Entity.Suscripcion;
import Grupo05.FitMindSet.dto.SuscripcionDTO;
import org.springframework.stereotype.Component;

@Component
public class SuscripcionMapper {

    public static SuscripcionDTO toDTO(Suscripcion suscripcion) {
        SuscripcionDTO dto = new SuscripcionDTO();
        dto.setId(suscripcion.getId());
        dto.setCustomerId(suscripcion.getCustomer().getId());
        dto.setPaypalPlanId(dto.getPaypalPlanId());
        dto.setFechaInicio(suscripcion.getFechainicio());
        dto.setFechaFin(suscripcion.getFechafin());
        dto.setMonto(suscripcion.getPrecio());
        dto.setOrderId(suscripcion.getOrderId());
        return dto;
    }

    public static Suscripcion toEntity(SuscripcionDTO dto) {
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setId(dto.getId());
        return suscripcion;
    }
}