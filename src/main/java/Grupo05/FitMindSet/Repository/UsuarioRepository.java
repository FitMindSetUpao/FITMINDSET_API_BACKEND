package Grupo05.FitMindSet.Repository;

import Grupo05.FitMindSet.domain.Entity.Customer;
import Grupo05.FitMindSet.domain.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findOneByCorreo(String correo);
    boolean existsByCorreo(String correo);
    Optional<Usuario> findByEmail(String correo);

}