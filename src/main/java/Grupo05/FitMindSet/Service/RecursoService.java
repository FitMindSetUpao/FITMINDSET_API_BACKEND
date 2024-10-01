package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.domain.Entity.Recurso;
import Grupo05.FitMindSet.dto.request.RecursoUpdateRequestDTO;
import Grupo05.FitMindSet.dto.response.RecursoResponseDTO;
import Grupo05.FitMindSet.Repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

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
}
