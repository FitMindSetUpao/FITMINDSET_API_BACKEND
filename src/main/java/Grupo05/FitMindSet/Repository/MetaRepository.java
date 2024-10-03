package Grupo05.FitMindSet.Repository;

import Grupo05.FitMindSet.domain.Entity.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Long> {
    // Métodos personalizados si es necesario
}
