package Grupo05.FitMindSet.Repository;

import Grupo05.FitMindSet.domain.Entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreAndApellidos(String nombre, String apellido);

    boolean existsByNombreAndApellidos(String nombre, String apellido);

    // Método para verificar si ya existe un autor con el mismo nombre y apellido, excepto el usuario actual
    boolean existsByNombreAndApellidosAndIdNot(String nombre, String apellido, Long id);

}
