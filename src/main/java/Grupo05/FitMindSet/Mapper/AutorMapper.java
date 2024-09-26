package Grupo05.FitMindSet.Mapper;

import Grupo05.FitMindSet.domain.Entity.Autor;
import Grupo05.FitMindSet.dto.AutorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AutorMapper {
    private final ModelMapper modelMapper;
    public AutorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public AutorDTO toDTO(Autor autor) {
        return modelMapper.map(autor, AutorDTO.class);
    }
    public Autor toEntity(AutorDTO autorDTO){
        return modelMapper.map(autorDTO,Autor.class)

    }
}
