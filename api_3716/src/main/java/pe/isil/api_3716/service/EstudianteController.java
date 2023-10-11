package pe.isil.api_3716.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.api_3716.model.Estudiante;
import pe.isil.api_3716.model.Periodo;
import pe.isil.api_3716.model.Profesor;
import pe.isil.api_3716.repository.EstudianteRepository;
import pe.isil.api_3716.repository.PeriodoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_rest/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    //ADD
    @PostMapping("/add")
    public Estudiante store(@RequestBody Estudiante estudiante){ //@requebody para recibir json, xml

        estudiante.setId(0);
        return estudianteRepository.save(estudiante);

    }

    @GetMapping("/estudianteByEmail")
    public Optional<Estudiante> getEstudiante(@RequestParam String correo) {
        return estudianteRepository.findByCorreo(correo);
    }

    @GetMapping("/estudianteByDni")
    public Optional<Estudiante> getEstudianteByDni(@RequestParam String dni) {
        return estudianteRepository.findByDni(dni);
    }


    //UODATE
    @PutMapping("/update/{id}")
    public Estudiante update(@RequestBody Estudiante estudiante, @PathVariable("id") Integer id){

        //1. obtenemos el registro del profesor de la bases de datops por el id
        Optional<Estudiante> estudianteFromDB = estudianteRepository.findById(id);

        //2. actualizar los campos del profesor de la base datos
        estudiante.setId(estudianteFromDB.get().getId());

        //3. ejecutamos la actualizacion con el metodo SAVE
        return estudianteRepository.save(estudiante);
    }


    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Estudiante> delete(@PathVariable("id") Integer id){
        estudianteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


    //GETALL
    @GetMapping("/getAll")
    public List<Estudiante> getAll(){

        return estudianteRepository.findAll();
    }


    //GETBYID
    @GetMapping("/getById/{id}")
    public Optional<Estudiante> getById(@PathVariable("id") Integer id){

        return estudianteRepository.findById(id);
    }




}
