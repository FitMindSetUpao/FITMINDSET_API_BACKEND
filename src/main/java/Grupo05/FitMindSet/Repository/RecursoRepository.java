package Grupo05.FitMindSet.Repository;

import Grupo05.FitMindSet.domain.Entity.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {

    // Buscar recursos por nombre o descripción
    List<Recurso> findByNombreRecursoContainingIgnoreCaseOrDescripcionRecursoContainingIgnoreCase(String nombreRecurso, String descripcionRecurso);

    // Buscar recurso por ID y ID de autor
    @Query("SELECT r FROM Recurso r WHERE r.id = :id AND r.autor.id = :autorId")
    Optional<Recurso> findByIdAndAutorId(@Param("id") Long id, @Param("autorId") Long autorId);

    // Buscar todos los recursos por ID de autor
    List<Recurso> findByAutorId(Long autorId);

    // Buscar recurso por ID
    Optional<Recurso> findById(Long id);
}
