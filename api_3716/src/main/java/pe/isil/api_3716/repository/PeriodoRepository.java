package pe.isil.api_3716.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.api_3716.model.Periodo;

@Repository
public interface PeriodoRepository extends JpaRepository<Periodo, Integer> {
}
