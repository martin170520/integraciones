package pe.isil.api_3716.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.api_3716.model.Periodo;
import pe.isil.api_3716.repository.PeriodoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api_rest/periodos")
public class PeriodoController {

    @Autowired
    private PeriodoRepository periodoRepository;

    //ADD
    @PostMapping("/add")
    public Periodo store(@RequestBody Periodo periodo){ //@requebody para recibir json, xml

        periodo.setId(0);
        return periodoRepository.save(periodo);

    }


    //UODATE
    @PutMapping("/update/{id}")
    public Periodo update(@RequestBody Periodo periodo, @PathVariable("id") Integer id){

         Periodo periodoFromBD = periodoRepository.getReferenceById(id);

         periodoFromBD.setNombre(periodo.getNombre());

         return periodoRepository.save(periodoFromBD); //trayendo de bd y tiene id
    }


    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Periodo> delete(@PathVariable("id") Integer id){
        periodoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


    //GETALL
    @GetMapping("/getAll")
    public List<Periodo> getAll(){
        return periodoRepository.findAll();
    }


    //GETBYID
    @GetMapping("/getById/{id}")
    public Optional <Periodo> getById(@PathVariable("id") Integer id){
        return periodoRepository.findById(id);
    }


}
