package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.Service.UsuarioService;
import Grupo05.FitMindSet.dto.response.UsuarioResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/perfiles")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        UsuarioResponseDTO usuarioPerfilResponseDTO = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuarioPerfilResponseDTO);
    }
}
