package Grupo05.FitMindSet.Repository;

import Grupo05.FitMindSet.domain.Entity.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Long> {
    // Métodos personalizados si es necesario
}