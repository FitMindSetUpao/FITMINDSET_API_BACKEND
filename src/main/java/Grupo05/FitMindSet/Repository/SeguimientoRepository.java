package Grupo05.FitMindSet.Repository;

import Grupo05.FitMindSet.domain.Entity.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Long> {

    List<Seguimiento> findByMetaUsuarioId(Long usuarioId);
}