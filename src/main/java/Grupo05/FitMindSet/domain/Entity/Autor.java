package Grupo05.FitMindSet.domain.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Especialidad", nullable = false, length = 50)
    private String especialidad;

    @OneToMany(mappedBy = "autor")
    private List<Recurso> recursos;
    @OneToOne
    @JoinColumn(name = "Usuario_id",referencedColumnName = "id")
    private Usuario usuario;
}

