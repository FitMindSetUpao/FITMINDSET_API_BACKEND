package Grupo05.FitMindSet.domain.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "NotificacionesPreferencias")
public class NotificacionePreferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Customer_id", nullable = false)
    private Customer customer;

    private boolean notifyOnRegistration;
    private boolean notifyOnProfileDeletion;
    private boolean notifyOnGoals;
    private boolean notifyOnPurchases;
}
