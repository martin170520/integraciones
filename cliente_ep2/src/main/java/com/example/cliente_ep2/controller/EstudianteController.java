package com.example.cliente_ep2.controller;


import com.example.cliente_ep2.model.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private RestTemplate restTemplate; //Permite consumir API REST

    //Mapear las rutas de los microservicios API REST - Estudiantes
    private static final String GET_ALL_ESTUDIANTE_API = "http://localhost:8090/api_rest/estudiantes/getAll";
    private static final String GET_BY_ID_ESTUDIANTE_API = "http://localhost:8090/api_rest/estudiantes/getById/{id}";
    private static final String ADD_ESTUDIANTE_API = "http://localhost:8090/api_rest/estudiantes/add";
    private static final String UPDATE_ESTUDIANTE_API = "http://localhost:8090/api_rest/estudiantes/update/{id}";
    private static final String DELETE_ESTUDIANTE_API = "http://localhost:8090/api_rest/estudiantes/delete/{id}";
    private static final String FIND_BY_EMAIL_API = "http://localhost:8090/api_rest/estudiantes/estudianteByEmail?correo={correo}";
    private static final String FIND_BY_DNI_API = "http://localhost:8090/api_rest/estudiantes/estudianteByDni?dni={dni}";

    @GetMapping("")
    public String index(Model model){
        ResponseEntity<Estudiante[]> listado = restTemplate.getForEntity(GET_ALL_ESTUDIANTE_API, Estudiante[].class);

        //Enviar el listado a la vista a traves del model
        model.addAttribute("estudiantes", listado.getBody());
        return  "estudiantes/index"; //retorna a la vista  index.html

    }


    @GetMapping("/nuevo") //para que me muestri un nuevo espacion vacio
    public String nuevo(Model model)
    {

        model.addAttribute("estudiante", new Estudiante());
        return "estudiantes/nuevo"; //retornamos  la vista del nuevo HTML
    }


    @PostMapping("/store")
    public String store(@Validated Estudiante estudiante, BindingResult binding, Model model, RedirectAttributes ra)
    {
        // Trae el estudiante validado, binding result detecta si hay errores

        if (binding.hasErrors()){
            model.addAttribute("estudiante", estudiante);
            return "estudiantes/nuevo";
        }


        ResponseEntity<Estudiante> estudianteToFind = restTemplate
                .getForEntity(FIND_BY_EMAIL_API, Estudiante.class, estudiante.getCorreo());

        if (estudianteToFind.getBody() != null) {
            if (estudianteToFind.getBody().getCorreo().equals(estudiante.getCorreo())) {
                binding.rejectValue("correo", "EmailAlreadyExists");
                return "estudiantes/nuevo";
            }
        }
        ResponseEntity<Estudiante> estudianteToFind2 = restTemplate
                .getForEntity(FIND_BY_DNI_API, Estudiante.class, estudiante.getDni());

        if (estudianteToFind2.getBody() != null) {
            if (estudianteToFind2.getBody().getDni().equals(estudiante.getDni())) {
                binding.rejectValue("dni", "DniAlreadyExists");
                return "estudiantes/nuevo";
            }
        }

        //Usar el restTamplate para llamar el metodo add y registrar un nuevo estudiante

        restTemplate.postForEntity(ADD_ESTUDIANTE_API, estudiante, Estudiante.class);

        ra.addFlashAttribute("msgExito", "Estudiante registrado exitosamente");

        return "redirect:/estudiantes"; //redireccionamos a la ruta /estudiantes*/

    }

    @GetMapping("/editar/{id}")
    public String editar(Model model , @PathVariable("id") Integer id)
    {
        Estudiante estudiante = restTemplate.getForObject(GET_BY_ID_ESTUDIANTE_API, Estudiante.class, id);
        model.addAttribute("estudiante", estudiante);
        return "estudiantes/editar";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable("id") Integer id, @Validated Estudiante estudiante, BindingResult binding, RedirectAttributes ra, Model model)
    {

        if (binding.hasErrors()){
            model.addAttribute("estudiante", estudiante);
            return "estudiantes/editar";
        }

        restTemplate.put(UPDATE_ESTUDIANTE_API, estudiante, id);
        ra.addFlashAttribute("msgExito", "Estudiante actualizado correctamente");
        return  "redirect:/estudiantes";
    }


    @PostMapping("/eliminar/{id}")
    public  String eliminar(@PathVariable("id") Integer id, RedirectAttributes ra)
    {
        restTemplate.delete(DELETE_ESTUDIANTE_API, id);
        ra.addFlashAttribute("msgExito" , "Estudiante eliminado correctamente");
        return "redirect:/estudiantes";

    }



}
