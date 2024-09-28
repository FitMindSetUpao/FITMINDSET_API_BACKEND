
package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.domain.Entity.Usuario;
import Grupo05.FitMindSet.Repository.UsuarioRepository;
import Grupo05.FitMindSet.Repository.MetaRepository;
import Grupo05.FitMindSet.dto.response.ModificarMetaResponseDTO;
import Grupo05.FitMindSet.dto.request.ModificarMetaRequestDTO;
import Grupo05.FitMindSet.domain.Entity.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private MetaRepository metaRepository;

    public ModificarMetaResponseDTO modificarMeta(ModificarMetaRequestDTO request) {
        Meta meta = metaRepository.findById(request.getMetaId())
                .orElseThrow(() -> new RuntimeException("Meta no encontrada"));

        meta.setDescripcion(request.getNuevaDescripcion());
        meta.setFechaFin(request.getNuevaFechaFin());
        metaRepository.save(meta);

        ModificarMetaResponseDTO response = new ModificarMetaResponseDTO();
        response.setMetaId(meta.getId());
        response.setEstado("Meta actualizada exitosamente");

        return response;
    }
}
