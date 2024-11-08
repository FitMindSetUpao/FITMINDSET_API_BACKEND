package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.domain.Entity.Habito;
import Grupo05.FitMindSet.domain.Entity.Meta;
import Grupo05.FitMindSet.dto.Request.MetaDTO;
import Grupo05.FitMindSet.dto.Response.MetaResponseDTO;

import java.util.List;

public interface MetaService {
    MetaResponseDTO crearMeta(Long habitoId, MetaDTO metaDTO);
    MetaResponseDTO actualizarMeta(Long metaId, MetaDTO metaDTO);
    void eliminarMeta(Long metaId);
    List<MetaResponseDTO> obtenerMetasPorHabito(Long habitoId);
    Habito obtenerHabitoPorId(Long habitoId);
}
