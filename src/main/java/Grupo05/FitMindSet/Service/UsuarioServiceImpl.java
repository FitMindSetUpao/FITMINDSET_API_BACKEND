
package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.Repository.MetaRepository;
import Grupo05.FitMindSet.dto.response.RecompensaResponseDTO;
import Grupo05.FitMindSet.dto.request.CrearMetaRequestDTO;
import Grupo05.FitMindSet.domain.Entity.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// Importa LocalDateTime desde java.time
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private MetaRepository metaRepository;

    public RecompensaResponseDTO crearMetaYGenerarRecompensa(CrearMetaRequestDTO request) {
        // Se asume que ya tienes el usuario cargado
        Meta meta = new Meta();
        meta.setDescripcion(request.getDescripcionMeta());
        meta.setFechaInicio(LocalDateTime.now());
        meta.setFechaFin(request.getFechaFin());
        meta.setEstado("Pendiente");
        metaRepository.save(meta);

        RecompensaResponseDTO response = new RecompensaResponseDTO();
        response.setMetaId(meta.getId());
        response.setEstadoMeta("Meta creada exitosamente");

        // Lógica para recompensas
        if (metaCumplida(meta)) {
            response.setRecompensa("Has recibido una medalla por cumplir tu meta.");
        } else {
            response.setRecompensa("Aún no has cumplido tu meta. Sigue adelante.");
        }

        return response;
    }

    private boolean metaCumplida(Meta meta) {
        // Validación simple para comprobar si la meta fue cumplida
        return LocalDateTime.now().isAfter(meta.getFechaFin());
    }
}
