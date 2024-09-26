package Grupo05.FitMindSet.Security;

import Grupo05.FitMindSet.Repository.UsuarioRepository;
import Grupo05.FitMindSet.domain.Entity.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public CustomerUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: "+ correo));

        return UsuarioPrincipal.create(usuario);
    }
}
