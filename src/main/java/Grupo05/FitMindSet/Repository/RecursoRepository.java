package Grupo05.FitMindSet.Repository;
import Grupo05.FitMindSet.domain.Entity.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {

    List<Recurso> findByNombreRecursoContainingIgnoreCaseOrDescripcionRecursoContainingIgnoreCase(String nombreRecurso, String descripcionRecurso);
}