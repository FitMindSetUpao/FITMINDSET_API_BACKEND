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

    // Obtener todos los recursos
    public List<RecursoDTO> obtenerTodosLosRecursos() {
        List<Recurso> recursos = recursoRepository.findAll();
        return recursos.stream()
                .map(this::convertirARecursoDTO)
                .collect(Collectors.toList());
    }

    // Buscar recursos por nombre o descripción
    public List<RecursoDTO> buscarRecursos(String busqueda) {
        List<Recurso> recursos = recursoRepository.findByNombreRecursoContainingIgnoreCaseOrDescripcionRecursoContainingIgnoreCase(busqueda, busqueda);
        return recursos.stream()
                .map(this::convertirARecursoDTO)
                .collect(Collectors.toList());
    }

    // Obtener recursos por autor
    public List<RecursoDTO> obtenerRecursosPorAutor(Long autorId) {
        List<Recurso> recursos = recursoRepository.findByAutorId(autorId);
        return recursos.stream()
                .map(this::convertirARecursoDTO)
                .collect(Collectors.toList());
    }

    // Convertir entidad Recurso a DTO
    private RecursoDTO convertirARecursoDTO(Recurso recurso) {
        RecursoDTO dto = new RecursoDTO();
        dto.setRecursoId(recurso.getId());
        dto.setNombreRecurso(recurso.getNombreRecurso());
        dto.setDescripcionRecurso(recurso.getDescripcionRecurso());
        dto.setPrecioRecurso(recurso.getPrecioRecurso());
        dto.setTipoRecurso(recurso.getTipoRecurso().name());
        return dto;
    }
}
