package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.domain.Entity.Usuario;
import Grupo05.FitMindSet.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class SuscripcionService {

    private final UsuarioRepository usuarioRepository;

    public SuscripcionService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void cancelarSuscripcion(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setSuscripcion(null); // Cancelar la suscripción
        usuarioRepository.save(usuario);
    }
}
