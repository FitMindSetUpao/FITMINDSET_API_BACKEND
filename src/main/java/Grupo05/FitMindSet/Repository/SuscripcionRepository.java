package Grupo05.FitMindSet.Repository;

import Grupo05.FitMindSet.domain.Entity.Customer;
import Grupo05.FitMindSet.domain.Entity.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {
    @Query("SELECT s FROM Suscripcion s WHERE s.fechafin < :fecha")
    List<Suscripcion> findByExpiryDateBefore(@Param("fecha") LocalDateTime fecha);
    List<Suscripcion> findByCustomerAndActiva(Customer customer, boolean activa);

}