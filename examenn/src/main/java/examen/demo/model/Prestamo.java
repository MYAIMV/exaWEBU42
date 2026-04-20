package examen.demo.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
@Entity @Data
public class Prestamo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaSolicitud;
    private LocalDate fechaDevolucion;
    @ManyToOne private Usuario usuario;
    @ManyToOne private Equipo equipo;
    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;
}