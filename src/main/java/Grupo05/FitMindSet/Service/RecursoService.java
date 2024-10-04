package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.domain.Entity.Recurso;
import Grupo05.FitMindSet.dto.request.RecursoDTO;
import Grupo05.FitMindSet.dto.request.RecursoUpdateRequestDTO;
import Grupo05.FitMindSet.dto.response.RecursoResponseDTO;
import Grupo05.FitMindSet.Repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    // Actualizar un recurso
    public RecursoResponseDTO actualizarRecurso(Long recursoId, RecursoUpdateRequestDTO request) {
        // Buscar el recurso por ID
        Optional<Recurso> optionalRecurso = recursoRepository.findById(recursoId);

        if (optionalRecurso.isPresent()) {
            Recurso recurso = optionalRecurso.get();

            // Actualizar los campos
            recurso.setNombreRecurso(request.getNombreRecurso());
            recurso.setDescripcionRecurso(request.getDescripcionRecurso());
            recurso.setPrecioRecurso(request.getPrecioRecurso());
            recurso.setTipoRecurso(request.getTipoRecurso());

            // Guardar el recurso actualizado en la base de datos
            recursoRepository.save(recurso);

            // Devolver la respuesta con el recurso actualizado
            RecursoResponseDTO response = new RecursoResponseDTO();
            response.setRecursoid(recurso.getRecursoid());
            response.setNombreRecurso(recurso.getNombreRecurso());
            response.setDescripcionRecurso(recurso.getDescripcionRecurso());
            response.setPrecioRecurso(recurso.getPrecioRecurso());
            response.setTipoRecurso(recurso.getTipoRecurso());

            return response;
        } else {
            throw new RuntimeException("Recurso no encontrado con ID: " + recursoId);
        }
    }

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
