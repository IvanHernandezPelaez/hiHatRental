package com.proyecto.hihatrental.controlador;

import com.proyecto.hihatrental.dto.CategoriaRespuestaDTO;
import com.proyecto.hihatrental.entidad.Categoria;
import com.proyecto.hihatrental.servicio.ServicioCategoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
public class ControladorCategoria {
    private ServicioCategoria servicioCategoria;

    public ControladorCategoria(ServicioCategoria servicioCategoria) {
        this.servicioCategoria = servicioCategoria;
    }
    @PostMapping("/registrar-categoria")
    public ResponseEntity<CategoriaRespuestaDTO> crearCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.ok(servicioCategoria.registrarCategoria(categoria));
    }
}
