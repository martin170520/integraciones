package pe.isil.api_3716.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.api_3716.model.Estudiante;

import java.util.Optional;


@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    Optional<Estudiante> findByCorreo(String correo);
}
