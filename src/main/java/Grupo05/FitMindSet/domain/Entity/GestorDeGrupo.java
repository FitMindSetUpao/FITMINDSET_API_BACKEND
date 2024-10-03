package Grupo05.FitMindSet.domain.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "GestorDeGrupos")
public class GestorDeGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GestorDeGruposID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_GrupoID", nullable = false)
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "FK_CustomerID", nullable = false)
    private Customer customer;
}
