package pe.isil.api_3716.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Profesor {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombres;
    private String apellidos;
    private String dni;
    private String correo;
    private String profesion;


}
