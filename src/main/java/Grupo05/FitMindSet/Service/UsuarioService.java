
package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.Exception.UserNotFoundException;
import Grupo05.FitMindSet.domain.Entity.Usuario;
import Grupo05.FitMindSet.Repository.UsuarioRepository;
import Grupo05.FitMindSet.dto.response.UsuarioResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    public UsuarioResponseDTO obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontro el usuario con el ID: " + id));

        return convertirAResponseDTO(usuario);
    }

    private UsuarioResponseDTO convertirAResponseDTO(Usuario usuario) {
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
        responseDTO.setId(usuario.getId());
        responseDTO.setNombre(usuario.getNombre());
        responseDTO.setApellidos(usuario.getApellidos());
        responseDTO.setCorreo(usuario.getCorreo());
        responseDTO.setEdad(usuario.getEdad());
        responseDTO.setGenero(usuario.getGenero());

        return responseDTO;
    }
}

