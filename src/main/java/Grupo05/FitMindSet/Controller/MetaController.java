package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.dto.request.ModificarMetaRequestDTO;
import Grupo05.FitMindSet.dto.response.ModificarMetaResponseDTO;
import Grupo05.FitMindSet.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meta")
public class MetaController {

    @Autowired
    private UsuarioService usuarioService;

    @PutMapping("/modificar")
    public ResponseEntity<ModificarMetaResponseDTO> modificarMeta(@RequestBody ModificarMetaRequestDTO request) {
        ModificarMetaResponseDTO response = usuarioService.modificarMeta(request);
        return ResponseEntity.ok(response);
    }
     @PutMapping("/actualizar/{metaId}")
    public ResponseEntity<MetaResponseDTO> actualizarMeta(@PathVariable Long metaId, @RequestBody MetaDTO metaDTO) {
        MetaResponseDTO metaActualizada = metaService.actualizarMeta(metaId, metaDTO);
        return new ResponseEntity<>(metaActualizada, HttpStatus.OK);
    }
     @DeleteMapping("/eliminar/{metaId}")
    public ResponseEntity<String> eliminarMeta(@PathVariable Long metaId) {
        metaService.eliminarMeta(metaId);
        return new ResponseEntity<>("Meta eliminada con éxito y notificación enviada", HttpStatus.NO_CONTENT);
    }
}
