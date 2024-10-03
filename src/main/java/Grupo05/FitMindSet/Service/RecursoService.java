package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.Repository.RecursoRepository;
import Grupo05.FitMindSet.domain.Entity.Recurso;
import Grupo05.FitMindSet.dto.request.RecursoDTO;

import java.util.List;

public class RecursoService {
    private RecursoRepository recursoRepository;

    public List<RecursoDTO> obtenerRecursosPorAutor(Long autorId) {
        List<Recurso> recursos = recursoRepository.findByAutorId(autorId);
        return recursos.stream().map(this::mapToDTO).toList();
    }

    private RecursoDTO mapToDTO(Recurso recurso) {
        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setRecursoId(recurso.getId());
        recursoDTO.setNombreRecurso(recurso.getNombreRecurso());
        recursoDTO.setDescripcionRecurso(recurso.getDescripcionRecurso());
        recursoDTO.setPrecioRecurso(recurso.getPrecioRecurso());
        recursoDTO.setTipoRecurso(recurso.getTipoRecurso().name());
        return recursoDTO;
    }
}

}
