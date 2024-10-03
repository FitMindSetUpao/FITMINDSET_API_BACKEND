package Grupo05.FitMindSet.domain.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GrupoId") // Cambiado de GestorDeGrupoId a GrupoId para reflejar el nombre correcto.
    private Long grupoId;

    @Column(name = "Titulo", nullable = false, length = 50)
    private String titulo;

    @Column(name = "Descripcion", nullable = true, length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios; // Relación con los comentarios.

    @OneToMany(mappedBy = "grupo")
    private List<GestorDeGrupo> gestores;
}
