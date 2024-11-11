package Grupo05.FitMindSet.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "purchase_items")
public class PurchaseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float price;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "recurso_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_purchase_item_recurso"))
    private Recurso recurso;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "purchase_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_purchase_item_purchase"))
    private Purchase purchase;
}
