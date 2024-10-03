package Grupo05.FitMindSet.Repository;

import Grupo05.FitMindSet.domain.Entity.Customer;
import Grupo05.FitMindSet.domain.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Customer, Long> {
    Optional<Usuario> findByCorreo(String correo);
}