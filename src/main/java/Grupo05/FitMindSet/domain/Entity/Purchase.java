package Grupo05.FitMindSet.domain.Entity;

import Grupo05.FitMindSet.domain.Enum.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customer customer;

    private Float monto;
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<PurchaseItem> items;
    @Column(name = "order_id")
    private String orderId;

}
