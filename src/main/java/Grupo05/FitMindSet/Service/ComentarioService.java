package Grupo05.FitMindSet.service;

import Grupo05.FitMindSet.domain.Entity.Comentario;
import Grupo05.FitMindSet.domain.Entity.Customer;
import Grupo05.FitMindSet.domain.Entity.Grupo;
import Grupo05.FitMindSet.dto.request.ComentarioDTO;
import Grupo05.FitMindSet.Repository.ComentarioRepository;
import Grupo05.FitMindSet.Repository.CustomerRepository;
import Grupo05.FitMindSet.Repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Comentario crearComentario(ComentarioDTO comentarioDTO) {
        Grupo grupo = grupoRepository.findById(comentarioDTO.getGrupoId())
                .orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado"));

        Customer customer = customerRepository.findById(comentarioDTO.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Comentario comentario = new Comentario();
        comentario.setContenido(comentarioDTO.getContenido());
        comentario.setFechaPublicacion(LocalDateTime.now());
        comentario.setGrupo(grupo);
        comentario.setCustomer(customer);

        return comentarioRepository.save(comentario);
    }
}