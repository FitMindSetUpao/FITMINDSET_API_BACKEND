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
    @Column(name = "Nombre", nullable = true, length = 100)
    private String nombre;

    @Column(name = "Apellidos", nullable = false, length = 100)
    private String apellidos;
    @Column(name = "Edad")
    private int edad;

    @Column(name = "Genero")
    private String genero;
    @Column(name = "Especialidad", nullable = false, length = 50)
    private String especialidad;

    @OneToMany(mappedBy = "autor")
    private List<Recurso> recursos;
    @OneToOne
    @JoinColumn(name = "Usuario_id",referencedColumnName = "id")
    private Usuario usuario;
}

