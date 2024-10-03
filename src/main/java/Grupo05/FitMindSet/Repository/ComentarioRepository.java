package Grupo05.FitMindSet.Repository;
import Grupo05.FitMindSet.domain.Entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
}
