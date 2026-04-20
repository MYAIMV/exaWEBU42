package examen.demo.service;
import examen.demo.model.*;
import examen.demo.dto.PrestamoDTO;
import examen.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {
    @Autowired private PrestamoRepository pRepo;
    @Autowired private UsuarioRepository uRepo;
    @Autowired private EquipoRepository eRepo;

    public Prestamo solicitarPrestamo(PrestamoDTO dto) {
        Usuario usuario = uRepo.findById(dto.getUsuarioId()).orElseThrow();
        Equipo equipo = eRepo.findById(dto.getEquipoId()).orElseThrow();
        long activos = pRepo.countByUsuarioIdAndEstado(usuario.getId(), EstadoPrestamo.APROBADO);

        Prestamo p = new Prestamo();
        p.setUsuario(usuario);
        p.setEquipo(equipo);
        p.setFechaSolicitud(LocalDate.now());

        if (usuario.isActivo() && equipo.isDisponible() && activos < 2) {
            p.setEstado(EstadoPrestamo.SOLICITADO);
        } else {
            p.setEstado(EstadoPrestamo.RECHAZADO);
        }
        return pRepo.save(p);
    }

    public void aprobarPrestamo(Long id) {
        Prestamo p = pRepo.findById(id).orElseThrow();
        if (p.getEstado() == EstadoPrestamo.SOLICITADO) {
            p.setEstado(EstadoPrestamo.APROBADO);
            p.getEquipo().setDisponible(false);
            pRepo.save(p);
        }
    }

    public void registrarDevolucion(Long id) {
        Prestamo p = pRepo.findById(id).orElseThrow();
        p.setEstado(EstadoPrestamo.DEVUELTO);
        p.getEquipo().setDisponible(true);
        pRepo.save(p);
    }

    public void rechazarPrestamo(Long id) {
        Prestamo p = pRepo.findById(id).orElseThrow();
        if (p.getEstado() == EstadoPrestamo.SOLICITADO) {
            p.setEstado(EstadoPrestamo.RECHAZADO);
            pRepo.save(p);
        }
    }

    public List<Equipo> buscarEquipoDisponiblePorNombre(String nombre) {
        return eRepo.findByNombreContainingAndDisponibleTrue(nombre);
    }
}