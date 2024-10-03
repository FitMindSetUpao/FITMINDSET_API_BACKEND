package Grupo05.FitMindSet.Service;

import Grupo05.FitMindSet.domain.Entity.Usuario;
import Grupo05.FitMindSet.domain.Entity.Seguimiento;
import Grupo05.FitMindSet.domain.Entity.Meta;
import Grupo05.FitMindSet.Repository.UsuarioRepository;
import Grupo05.FitMindSet.Repository.SeguimientoRepository;
import Grupo05.FitMindSet.domain.Enum.Estado;
import Grupo05.FitMindSet.Repository.MetaRepository;
import Grupo05.FitMindSet.dto.response.RegistroActividadResponseDTO;
import Grupo05.FitMindSet.dto.request.RegistroActividadRequestDTO;
import Grupo05.FitMindSet.Exception.RoleNotFoundException;
import Grupo05.FitMindSet.dto.AuthResponseDTO;
import Grupo05.FitMindSet.dto.LoginDTO;
import Grupo05.FitMindSet.dto.UserProfileDTO;
import Grupo05.FitMindSet.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Autowired
    private MetaRepository metaRepository;

    @Override
    public RegistroActividadResponseDTO registrarActividad(RegistroActividadRequestDTO request) {
        Meta meta = metaRepository.findById(request.getMetaId())
                .orElseThrow(() -> new RuntimeException("Meta no encontrada"));

        Seguimiento seguimiento = new Seguimiento();
        seguimiento.setMeta(meta);
        seguimiento.setFecha(request.getFecha());
        seguimiento.setPorcentaje(request.getPorcentajeCumplido());
        seguimiento.setTiempoCumplido(request.getTiempoCumplido());
        seguimiento.setEstado(Estado.ENPROCESO); // Estado Enum

        seguimientoRepository.save(seguimiento);

        RegistroActividadResponseDTO response = new RegistroActividadResponseDTO();
        response.setSeguimientoId(seguimiento.getId());
        response.setFecha(seguimiento.getFecha());
        response.setPorcentajeCumplido(seguimiento.getPorcentaje());
        response.setTiempoCumplido(seguimiento.getTiempoCumplido());
        response.setEstado("Actividad registrada correctamente");

        return response;
    }

    // Métodos de la interfaz
    @Override
    public UserProfileDTO registerCustomer(UserRegistrationDTO registrationDTO) throws RoleNotFoundException {
        // Implementación del registro de cliente
        return null; // Lógica por implementar
    }

    @Override
    public UserProfileDTO registerAuthor(UserRegistrationDTO registrationDTO) throws RoleNotFoundException {
        // Implementación del registro de autor
        return null; // Lógica por implementar
    }

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        // Implementación del login
        return null; // Lógica por implementar
    }

    @Override
    public UserProfileDTO updateUserProfile(Long id, UserProfileDTO userProfileDTO) {
        // Implementación de actualización de perfil de usuario
        return null; // Lógica por implementar
    }

    @Override
    public UserProfileDTO getUserProfileById(Long id) {
        // Implementación de obtención de perfil de usuario
        return null; // Lógica por implementar
    }

    @Override
    public boolean isEmailRegistered(String correo) {
        // Implementación para verificar si el correo está registrado
        return false; // Lógica por implementar
    }
}
