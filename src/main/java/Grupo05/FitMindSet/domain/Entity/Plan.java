package Grupo05.FitMindSet.domain.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "Plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PlanID")
    private Long id;

    @Column(name = "Nombre_Plan", nullable = false)
    private String nombrePlan;

    @Column(name = "Especificacion", nullable = false)
    private String especificacion;

    @Column(name = "Costo", nullable = true)
    private BigDecimal costo;

    @OneToMany(mappedBy = "plan")
    private List<Suscripcion> suscripciones;
}
