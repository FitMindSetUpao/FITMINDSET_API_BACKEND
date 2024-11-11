package Grupo05.FitMindSet.Service;
import Grupo05.FitMindSet.domain.Entity.Recurso;
import Grupo05.FitMindSet.dto.request.RecursoDTO;
import Grupo05.FitMindSet.Repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    public List<RecursoDTO> obtenerTodosLosRecursos() {
        List<Recurso> recursos = recursoRepository.findAll();
        return recursos.stream()
                .map(this::convertirARecursoDTO)
                .collect(Collectors.toList());
    }

    public List<RecursoDTO> buscarRecursos(String busqueda) {
        List<Recurso> recursos = recursoRepository.findByNombreRecursoContainingIgnoreCaseOrDescripcionRecursoContainingIgnoreCase(busqueda, busqueda);
        return recursos.stream()
                .map(this::convertirARecursoDTO)
                .collect(Collectors.toList());
    }

    private RecursoDTO convertirARecursoDTO(Recurso recurso) {
        RecursoDTO dto = new RecursoDTO();
        dto.setRecursoId(recurso.getRecursoid());
        dto.setNombreRecurso(recurso.getNombreRecurso());
        dto.setDescripcionRecurso(recurso.getDescripcionRecurso());
        dto.setPrecioRecurso(recurso.getPrecioRecurso());
        dto.setTipoRecurso(recurso.getTipoRecurso());
        return dto;
    }

}
