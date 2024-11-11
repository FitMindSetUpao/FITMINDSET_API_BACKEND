package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.domain.Entity.Comentario;
import Grupo05.FitMindSet.dto.request.ComentarioDTO;
import Grupo05.FitMindSet.service.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/crear")
    public ResponseEntity<Comentario> crearComentario(@Valid @RequestBody ComentarioDTO comentarioDTO) {
        Comentario comentario = comentarioService.crearComentario(comentarioDTO);
        return ResponseEntity.ok(comentario);
    }
}
