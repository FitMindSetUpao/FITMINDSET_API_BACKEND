package Grupo05.FitMindSet.Repository;

import Grupo05.FitMindSet.domain.Entity.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecursoRepository extends JpaRepository<Recurso, Long> {
    @Query("SELECT r FROM Recurso r WHERE r.id = :id AND r.autor.id = :autorId")
    Optional<Recurso> findByIdAndAutorId(@Param("id") Long id, @Param("autorId") Long autorId);
    List<Recurso> findByAutorId(Long autorId);
    Optional<Recurso> findById(Long id);
}
