package com.proyecto.hihatrental.controlador;

import com.proyecto.hihatrental.dto.CategoriaRespuestaDTO;
import com.proyecto.hihatrental.entidad.Categoria;
import com.proyecto.hihatrental.servicio.ServicioCategoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class ControladorCategoria {
    private ServicioCategoria servicioCategoria;

    public ControladorCategoria(ServicioCategoria servicioCategoria) {
        this.servicioCategoria = servicioCategoria;
    }
    @PostMapping
    public ResponseEntity<CategoriaRespuestaDTO> crearCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.ok().body(servicioCategoria.registrarCategoria(categoria));
    }

    @GetMapping
    public  ResponseEntity<List<CategoriaRespuestaDTO>> buscarCategorias(){
        return ResponseEntity.ok().body(servicioCategoria.buscarCategorias());
    }

    @GetMapping("/{id-categoria}")
    public  ResponseEntity<CategoriaRespuestaDTO> buscarCategoriaPorId(@PathVariable("id-categoria")Long id){
        return ResponseEntity.ok().body(servicioCategoria.buscarCategoriaPorId(id));
    }

    @PutMapping
    public ResponseEntity<String> actualizarCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.ok().body(servicioCategoria.actualizarCategoria(categoria));
    }

    @DeleteMapping("/{id-categoria}")
    public ResponseEntity<String> eliminarCategoriaPorId(@PathVariable("id-categoria")Long id, @RequestParam(defaultValue = "") String confirmacion){
        return ResponseEntity.ok().body(servicioCategoria.eliminarCategoria(id, confirmacion));
    }

}
