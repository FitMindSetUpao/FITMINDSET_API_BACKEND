package Grupo05.FitMindSet.Controller;
import Grupo05.FitMindSet.dto.request.RecursoDTO;
import Grupo05.FitMindSet.Service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    @GetMapping("/recursos")
    public ResponseEntity<List<RecursoDTO>> obtenerRecursos() {
        List<RecursoDTO> recursos = recursoService.obtenerTodosLosRecursos();
        if (recursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recursos);
    }

    @GetMapping("/recursos/buscar")
    public ResponseEntity<List<RecursoDTO>> buscarRecursos(@RequestParam String query) {
        List<RecursoDTO> recursos = recursoService.buscarRecursos(query);
        if (recursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recursos);
    }
}