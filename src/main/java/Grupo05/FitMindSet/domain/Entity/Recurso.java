package Grupo05.FitMindSet.domain.Entity;

import Grupo05.FitMindSet.domain.Enum.TipoRecurso;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name="Recursos")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_RecursoID")
    private Long recursoid;

    @Column(name = "NombreRecurso", nullable = true)
    private String nombreRecurso;

    @Column(name = "Descripcion", nullable = true)
    private String descripcionRecurso;

    @Column(name = "Precio", nullable = true)
    private BigDecimal precioRecurso;

    @Enumerated(EnumType.STRING)
    @Column(name = "TipoRecurso")
    private TipoRecurso tipoRecurso;

    @ManyToOne
    @JoinColumn(name = "FK_AutorID", nullable = true)
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "FK_TipoDeHabitoID", nullable = true)
    private TipoDeHabito tipoDeHabito;
}

