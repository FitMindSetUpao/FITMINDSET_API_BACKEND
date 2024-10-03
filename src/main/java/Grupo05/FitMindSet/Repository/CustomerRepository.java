package Grupo05.FitMindSet.Repository;

import Grupo05.FitMindSet.domain.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByNombreAndApellidos(String nombre,String apellido);
    boolean existsByNombreAndApellidosAndIdNot(String nombre,String apellido,Long id);
}
