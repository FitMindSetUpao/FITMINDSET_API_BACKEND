package Grupo05.FitMindSet.Repository;

import Grupo05.FitMindSet.domain.Entity.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecursoRepository extends JpaRepository<Recurso, Long> {
    List<Recurso> findByAutorNombre(String nombre);
}