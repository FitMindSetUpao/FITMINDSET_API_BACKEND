package Grupo05.FitMindSet.domain.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Suscripcion")
public class Suscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Fechainicio", nullable = false)
    private LocalDateTime fechainicio;
    @Column(name = "FechaFin", nullable = true)
    private LocalDateTime fechafin;
    @Column(name = "precio", nullable = false)
    private BigDecimal precio;
    @ManyToOne
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "PlanID", nullable = false)
    private Plan plan;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "Activa", nullable = false)
    private boolean activa;
    @Column(name = "Status", nullable = false)
    private String status;
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;
    @Column(name = "permite_descargar", nullable = false, columnDefinition = "boolean default false")
    private boolean permiteDescargar;
}