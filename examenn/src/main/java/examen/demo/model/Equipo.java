package examen.demo.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity @Data
public class Equipo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String tipo;
    private boolean disponible;
}