package Grupo05.FitMindSet.domain.Entity;

import Grupo05.FitMindSet.domain.Enum.ERole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Roles")
@Data
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false,unique = true)
    private ERole name;
}
