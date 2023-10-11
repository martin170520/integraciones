package pe.isil.api_3716.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String nombres;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String apellidos;

    @NotNull
    @Size(min = 8, max = 8)
    private String dni;

    @Email
    private String correo;

    @NotNull
    @NotBlank
    private Integer id_carrera;

}
