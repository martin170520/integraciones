package com.example.cliente_ep2.model;

import lombok.Data;

import javax.validation.constraints.*;



@Data
public class Estudiante {

    private Integer id;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 50)
    private String nombres;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 50)
    private String apellidos;

    @NotBlank
    @NotNull
    @Size(min = 8, max = 8)
    private String dni;

    @NotBlank
    @NotNull
    @Email
    private String correo;

    private Integer id_carrera;


}
