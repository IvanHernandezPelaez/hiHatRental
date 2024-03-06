package com.proyecto.hihatrental.controlador;

import com.proyecto.hihatrental.dto.RespuestaProductoDTO;
import com.proyecto.hihatrental.entidad.Producto;
import com.proyecto.hihatrental.servicio.ServicioProducto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ControladorProducto {


    private ServicioProducto servicioProducto;

    public ControladorProducto(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    @PostMapping("/registrar-producto")
    public ResponseEntity<RespuestaProductoDTO> registrarProducto(@RequestBody Producto producto){
        return ResponseEntity.ok(servicioProducto.registrarProducto(producto));
    }

    @GetMapping("/ver-productos")
    public ResponseEntity<List<RespuestaProductoDTO>> buscarProducto(){
        return ResponseEntity.ok().body(servicioProducto.buscarProductos());
    }

    @GetMapping("/{id-producto}")
    public ResponseEntity<RespuestaProductoDTO> buscarProductoPorId(@PathVariable("id-producto") long id){
        return ResponseEntity.ok().body(servicioProducto.buscarProductoPorId(id));
    }

    @PutMapping("/actualizar-producto")
    public ResponseEntity<String> actualizarProducto(@RequestBody Producto producto){
        return ResponseEntity.ok().body(servicioProducto.actualizarProducto(producto));
    }
    @DeleteMapping("/{id-eliminar}")
    public ResponseEntity<String> eliminarProducto(@PathVariable("id-eliminar") long id){
        return ResponseEntity.ok().body(servicioProducto.eliminarProducto(id));
    }




}
