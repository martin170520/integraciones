package pe.isil.api_3716.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.isil.api_3716.model.Curso;

@Repository  //simula ser una base de dato PORQUE trabaja con acceso a datos.
public interface CursoRepository extends JpaRepository <Curso, Integer> {


}
