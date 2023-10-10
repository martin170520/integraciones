package pe.isil.api_3716.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.api_3716.model.Curso;
import pe.isil.api_3716.model.Profesor;
import pe.isil.api_3716.repository.CursoRepository;

import java.util.List;
import java.util.Optional;

@RestController //para que sea un restController
@RequestMapping("/api_rest/cursos") //administrar la siguiente ruta:  localhost:8090/api_rest/cursos/

public class CursoController {

    @Autowired
    //objeto de tipo repository
    private CursoRepository cursoRepository;

    //OBTENER TODOS LOS CURSOS DE LA BD: SELECT * FROM CURSOS;
    @GetMapping("/getAll")  //escuchará: localhost:8090/api_rest/cursos/getAll
    public List<Curso> getAll(){
        return cursoRepository.findAll();
    }


    //Obtener un curso por su id, SELECT * FROM curso WHERE id=?
    @GetMapping("/getById/{id}") //localhost:8090/api_rest/cursos/getById/1
    public Optional<Curso> getById(@PathVariable("id") Integer id){
        return cursoRepository.findById(id);
    }


    //Insertamos un nuevo registro de curso
    @PostMapping("/store") //localhost:8090/api_rest/cursos/store (json)
    public Curso store(@RequestBody Curso curso){
        curso.setId(0); //incializamos el id con cero para asegurar que sea un insertar
        return cursoRepository.save(curso);
    }



    @PutMapping("/update/{id}")
    public Curso update(@RequestBody Curso curso, @PathVariable("id") Integer id){
        //1. obtenemos el registro del curso de la bases de datos por el id
        Curso cursoFromDB = cursoRepository.getById(id);

        //2. actualizar los campos del curso de la base datos
        cursoFromDB.setCodigo(curso.getCodigo());
        cursoFromDB.setNombre(curso.getNombre());
        cursoFromDB.setCreditos(curso.getCreditos());
        cursoFromDB.setHoras(curso.getHoras());

        //3. ejecutamos la actualizacion con el metodo SAVE
        return cursoRepository.save(cursoFromDB);
    }

    @PutMapping("/update2/{id}")
    public Curso update2(@RequestBody Curso curso, @PathVariable("id") Integer id){
        //1. obtenemos el registro del curso de la bases de datos por el id
        Optional<Curso> cursoFromDB = cursoRepository.findById(id);

        //2. actualizar los campos del curso de la base datos
        curso.setId(cursoFromDB.get().getId());

        //3. ejecutamos la actualizacion con el metodo SAVE
        return cursoRepository.save(curso);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Curso> delete(@PathVariable("id") Integer id){
        cursoRepository.deleteById(id);
        return ResponseEntity.ok().build();//mensaje del servidor
    }

}
