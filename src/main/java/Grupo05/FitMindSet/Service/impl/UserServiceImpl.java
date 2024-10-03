package Grupo05.FitMindSet.Service.impl;

import Grupo05.FitMindSet.Repository.UsuarioRepository;
import Grupo05.FitMindSet.domain.Entity.Usuario;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl {
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void eliminarUsuarioPorCorreo(String correo) {
        Usuario usuario = usuarioRepository.findOneByCorreo(correo)
                .orElseThrow(() -> new BadRequestException("Usuario no encontrado."));
        usuarioRepository.delete(usuario);
    }

    @Transactional
    public void delete(Long id) throws BadRequestException {
        Usuario usuario = usuarioRepository.findById(id)
        usuarioRepository.delete(usuario);
    }
    public String findEmailById(Long id) {
        Usuario user = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + id + " no encontrado"));
        return user.getCorreo();
    }

}
