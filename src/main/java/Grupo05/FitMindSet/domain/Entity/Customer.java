package Grupo05.FitMindSet.domain.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Nombre", nullable = true, length = 100)
    private String nombre;

    @Column(name = "Apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "Edad")
    private int edad;

    @Column(name = "Genero")
    private String genero;

    @ManyToOne
    @JoinColumn(name = "SuscripcionID")
    private Suscripcion suscripcion;

    @OneToMany(mappedBy = "customer")
    private List<Habito> habitos;

    @OneToMany(mappedBy = "customer")
    private List<Grupo> Grupos;

    @OneToMany(mappedBy = "customer")
    private List<GestorDeGrupo> gruposGestionados;

    @Column(name = "NotificationFrequency")
    private String notificationFrequency;

    @Column(name = "Peso")
    private Double peso;

    @Column(name = "Altura")
    private Double altura;

    @OneToOne(mappedBy = "customer")
    private Comentario comentario;

    // Método para calcular el IMC
    public Double calcularIMC() {
        if (peso != null && altura != null && altura > 0) {
            return peso / (altura * altura);
        }
        return null;
    }
    @OneToOne
    @JoinColumn(name = "Usuario_id",referencedColumnName = "id")
    private Usuario usuario;
}
