package Grupo05.FitMindSet.Controller;

import Grupo05.FitMindSet.dto.request.RegistroActividadRequestDTO;
import Grupo05.FitMindSet.dto.response.RegistroActividadResponseDTO;
import Grupo05.FitMindSet.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actividad")
public class ActividadController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para registrar la actividad diaria del usuario
    @PostMapping("/registrar")
    public ResponseEntity<RegistroActividadResponseDTO> registrarActividad(@RequestBody RegistroActividadRequestDTO request) {
        RegistroActividadResponseDTO response = usuarioService.registrarActividad(request);
        return ResponseEntity.ok(response);
    }
}
