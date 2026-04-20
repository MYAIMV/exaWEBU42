package examen.demo.repository;
import  examen.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    long countByUsuarioIdAndEstado(Long usuarioId, EstadoPrestamo estado);
}