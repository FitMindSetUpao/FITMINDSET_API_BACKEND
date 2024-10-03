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
    @Column(name = "ForoID")
    private Long foroId;

    @Column(name = "Titulo", nullable = false, length = 50)
    private String titulo;

    @Column(name = "Descripcion", nullable = true, length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @OneToMany(mappedBy = "grupo")
    private List<GestorDeGrupo> gestores;


    //Increible
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;
}
