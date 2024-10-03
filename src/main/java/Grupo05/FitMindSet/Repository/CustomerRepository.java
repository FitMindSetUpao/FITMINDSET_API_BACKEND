package Grupo05.FitMindSet.Repository;

import Grupo05.FitMindSet.domain.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByNombreAndApellidos(String nombre, String apellido);
    boolean existsByNombreAndApellidosAndIdNot(String nombre, String apellido, Long id);
}
