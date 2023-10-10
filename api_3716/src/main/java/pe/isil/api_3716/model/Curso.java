package pe.isil.api_3716.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalTime;

// los @xxx SE LLAMAN ANOTACIONES
@Data
@Entity
public class Curso {

    @Id // primary key
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer codigo;
    private String nombre;
    private Integer creditos;
    private LocalTime horas;

}
