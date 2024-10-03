package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.Repository.RecursoRepository;
import Grupo05.FitMindSet.domain.Entity.Recurso;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecursoService {

    private final RecursoRepository recursoRepository;

    public String obtenerNombreArchivoPorRecursoId(Long recursoId) {
        Recurso recurso = recursoRepository.findById(recursoId)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado con ID: " + recursoId));

        return recurso.getNombreRecurso();  // Aquí retornamos el nombre del archivo
    }
}
