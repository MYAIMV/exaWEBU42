package examen.demo.controller;
import examen.demo.dto.PrestamoDTO;
import examen.demo.model.*;
import examen.demo.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
    @Autowired private PrestamoService service;

    @PostMapping
    public ResponseEntity<Prestamo> solicitar(@RequestBody PrestamoDTO dto) {
        return ResponseEntity.ok(service.solicitarPrestamo(dto));
    }

    @PatchMapping("/{id}/aprobar")
    public void aprobar(@PathVariable Long id) { service.aprobarPrestamo(id); }

    @PatchMapping("/{id}/devolver")
    public void devolver(@PathVariable Long id) { service.registrarDevolucion(id); }

    @PatchMapping("/{id}/rechazar")
    public void rechazar(@PathVariable Long id) { service.rechazarPrestamo(id); }

    @GetMapping("/equipos/{nombre}")
    public List<Equipo> buscar(@PathVariable String nombre) {
        return service.buscarEquipoDisponiblePorNombre(nombre);
    }
}