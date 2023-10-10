package com.example.cliente_ep2.model;

import lombok.Data;

@Data
public class Estudiante {


    private Integer id;
    private String nombres;
    private String apellidos;
    private String dni;
    private String correo;
    private Integer id_carrera;


}
