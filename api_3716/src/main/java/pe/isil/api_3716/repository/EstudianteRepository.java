package pe.isil.api_3716.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.api_3716.model.Estudiante;


@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
}
