package Grupo05.FitMindSet.domain.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Comentario")
public class Comentario {

    @Id
    @Column(name = "ComentarioId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comentarioId;

    @Column(name = "Contenido", nullable = false, length = 255)
    private String contenido;

    @Column(name = "FechadePublicacion", nullable = false)
    private LocalDateTime fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "customer", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "grupo", nullable = false) // Cambiado a "grupo" para mantener consistencia
    private Grupo grupo;
}
