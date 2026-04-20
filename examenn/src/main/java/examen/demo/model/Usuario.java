package examen.demo.model;
import jakarta.persistence.*;
import lombok.Data; // Si no tienes Lombok, crea los Getters/Setters manualmente
@Entity @Data
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;
    private boolean activo;
}