package Grupo05.FitMindSet.Repository;

import Grupo05.FitMindSet.domain.Entity.Rol;
import Grupo05.FitMindSet.domain.Enum.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByName(ERole name);
}