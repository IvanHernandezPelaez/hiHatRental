package com.proyecto.hihatrental.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RespuestaProductoDTO {
    private String nombre;
    private String descripcion;
    private List<String> imagenes;

}
