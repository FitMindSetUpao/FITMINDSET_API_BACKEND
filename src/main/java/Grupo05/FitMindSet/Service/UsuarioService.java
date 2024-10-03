package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.domain.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioService extends JpaRepository<Usuario, Long> {
    void delete (Long id);
    void eliminarUsuarioPorCorreo(String correo);
}
