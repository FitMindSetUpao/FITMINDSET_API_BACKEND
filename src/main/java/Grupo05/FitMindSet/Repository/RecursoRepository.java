package Grupo05.FitMindSet.Repository;

import Grupo05.FitMindSet.domain.Entity.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {
    // Puedes agregar métodos personalizados si lo necesitas
}
