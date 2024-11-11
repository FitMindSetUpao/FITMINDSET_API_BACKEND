package Grupo05.FitMindSet.Service.impl;

import Grupo05.FitMindSet.Mapper.MetaMapper;
import Grupo05.FitMindSet.Repository.MetaRepository;
import Grupo05.FitMindSet.Service.EmailService;
import Grupo05.FitMindSet.Service.MetaService;
import Grupo05.FitMindSet.domain.Entity.Habito;
import Grupo05.FitMindSet.domain.Entity.Meta;
import Grupo05.FitMindSet.Repository.HabitoRepository;
import Grupo05.FitMindSet.dto.Request.MetaDTO;
import Grupo05.FitMindSet.dto.Response.MetaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetaServiceImpl implements MetaService {

    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private HabitoRepository habitoRepository;

    @Autowired
    private MetaMapper metaMapper;
    @Autowired
    private EmailService emailService;

    @Override
    public MetaResponseDTO crearMeta(Long habitoId, MetaDTO metaDTO) {

        Habito habito = habitoRepository.findById(habitoId)
                .orElseThrow(() -> new RuntimeException("Hábito no encontrado"));

        Meta nuevaMeta = metaMapper.toEntity(metaDTO);
        nuevaMeta.setHabito(habito);
        Meta savedMeta = metaRepository.save(nuevaMeta);


        return metaMapper.toResponseDTO(savedMeta);
    }

    @Override
    public Habito obtenerHabitoPorId(Long habitoId) {
        return habitoRepository.findById(habitoId)
                .orElseThrow(() -> new RuntimeException("Hábito no encontrado"));
    }
@Override
    public MetaResponseDTO actualizarMeta(Long metaId, MetaDTO metaDTO) {
        Meta metaExistente = metaRepository.findById(metaId)
                .orElseThrow(() -> new RuntimeException("Meta no encontrada"));
        metaExistente.setDescripcion(metaDTO.getDescripcion());
        metaExistente.setFechaInicio(metaDTO.getFechaInicio());
        metaExistente.setFechaFin(metaDTO.getFechaFin());
        Meta metaActualizada = metaRepository.save(metaExistente);
        return metaMapper.toResponseDTO(metaActualizada);
    } 
  @Transactional
    public void eliminarMeta(Long metaId) {
        // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUsuario = authentication.getName();
        Meta meta = metaRepository.findById(metaId)
                .orElseThrow(() -> new RuntimeException("Meta no encontrada"));

        metaRepository.delete(meta);

        emailService.sendAccountDeletionEmail(emailUsuario);
    }
