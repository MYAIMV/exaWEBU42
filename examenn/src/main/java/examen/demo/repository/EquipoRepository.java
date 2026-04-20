package examen.demo.repository;
import  examen.demo.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByNombreContainingAndDisponibleTrue(String nombre);
}