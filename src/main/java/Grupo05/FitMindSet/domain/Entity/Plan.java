package Grupo05.FitMindSet.domain.Entity;

import Grupo05.FitMindSet.domain.Enum.TipoSuscripcion;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "TipoSuscripcion", nullable = false)
    private TipoSuscripcion tipoSuscripcion;

    @Column(name = "Especificacion", nullable = false)
    private String especificacion;

    @Column(name = "precio", nullable = true)
    private BigDecimal precio;

    @OneToMany(mappedBy = "plan")
    private List<Recurso> recursos;

    @Column(name = "permite_descargar", nullable = false, columnDefinition = "boolean default false")
    private boolean permiteDescargar;

    @Column(name = "paypal_plan_id", nullable = false)
    private String paypalPlanId;

    public Plan() {
    }

    public Plan(TipoSuscripcion tipoSuscripcion, String especificacion, BigDecimal precio, boolean permiteDescargar, String paypalPlanId) {
        this.tipoSuscripcion = tipoSuscripcion;
        this.especificacion = especificacion;
        this.precio = precio;
        this.permiteDescargar = permiteDescargar;
        this.paypalPlanId = paypalPlanId;
    }
}