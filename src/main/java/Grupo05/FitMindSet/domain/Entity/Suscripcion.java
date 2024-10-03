package Grupo05.FitMindSet.domain.Entity;

import Grupo05.FitMindSet.domain.Enum.TipoSuscripcion;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "Suscripcion")
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_SuscripcionID")
    private Long id;

    @Column(name = "Fechainicio", nullable = false)
    private LocalDateTime fechainicio;

    @Column(name = "FechaFin", nullable = true)
    private LocalDateTime fechafin;

    @Column(name = "Monto", nullable = false)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "TipoSuscripcion", nullable = false)
    private TipoSuscripcion tipoSuscripcion;

    @ManyToOne
    @JoinColumn(name = "FK_Customer_Suscripcion", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "FK_Plan_Suscripcion", nullable = false)
    private Plan plan;

    @ManyToMany
    @JoinTable(
            name = "Suscripcion_ContenidoAdicional",
            joinColumns = @JoinColumn(name = "SuscripcionID"),
            inverseJoinColumns = @JoinColumn(name = "ContenidoAdicionalID")
    )
    private Set<ContenidoAdicional> contenidosAdicionales;
}
