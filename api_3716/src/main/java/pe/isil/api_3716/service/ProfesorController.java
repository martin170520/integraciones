package pe.isil.api_3716.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.api_3716.model.Profesor;
import pe.isil.api_3716.repository.ProfesorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_rest/profesores") //localhost:8090/api_rest/profesores/*
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    //Obtener todos los profesores de la base de datos. SELECT * FROM profesor;
    @GetMapping("/getAll") //localhost:8090/api_rest/profesores/getAll
    public List<Profesor> getAll(){
        return profesorRepository.findAll();
    }

    //Obtener un profesor por su id, SELECT * FROM profesor WHERE id=?
    @GetMapping("/getById/{id}") //localhost:8090/api_rest/profesores/getById/1
    public Optional<Profesor> getById(@PathVariable("id") Integer id){
        return profesorRepository.findById(id);
    }

    //Insertamos un nuevo registro de profesor
    @PostMapping("/store") //localhost:8090/api_rest/profesores/store (json)
    public Profesor store(@RequestBody Profesor profesor){
        profesor.setId(0); //incializamos el id con cero para asegurar que sea un insertar
        return profesorRepository.save(profesor);
    }

    @PutMapping("/update/{id}")
    public Profesor update(@RequestBody Profesor profesor, @PathVariable("id") Integer id){
        //1. obtenemos el registro del profesor de la bases de datops por el id
        Profesor profesorFromDB = profesorRepository.getById(id);

        //2. actualizar los campos del profesor de la base datos
        profesorFromDB.setNombres(profesor.getNombres());
        profesorFromDB.setDni(profesor.getDni());
        profesorFromDB.setApellidos(profesor.getApellidos());
        profesorFromDB.setCorreo(profesor.getCorreo());
        profesorFromDB.setProfesion(profesor.getProfesion());

        //3. ejecutamos la actualizacion con el metodo SAVE
        return profesorRepository.save(profesorFromDB);
    }

    @PutMapping("/update2/{id}")
    public Profesor update2(@RequestBody Profesor profesor, @PathVariable("id") Integer id){
        //1. obtenemos el registro del profesor de la bases de datops por el id
        Optional<Profesor> profesorFromDB = profesorRepository.findById(id);

        //2. actualizar los campos del profesor de la base datos
        profesor.setId(profesorFromDB.get().getId());

        //3. ejecutamos la actualizacion con el metodo SAVE
        return profesorRepository.save(profesor);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Profesor> delete(@PathVariable("id") Integer id){
        profesorRepository.deleteById(id);
        return ResponseEntity.ok().build();//mensaje del servidor
    }
}
