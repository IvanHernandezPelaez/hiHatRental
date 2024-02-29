package com.proyecto.hihatrental.servicio;

import com.proyecto.hihatrental.entidad.Imagen;
import com.proyecto.hihatrental.entidad.Producto;
import com.proyecto.hihatrental.repositorio.RepositorioProducto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioProducto {


    //@Autowired
    private RepositorioProducto repositorioProducto;

    public ServicioProducto(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }

    public Producto registrarProducto(Producto producto){
        boolean nombreExiste = repositorioProducto.existsByNombre(producto.getNombre());
        if (nombreExiste){
            throw new IllegalArgumentException("Nombre de producto ya existe en la BD");
        }

        List<Imagen> list = producto.getImagenes();

        for (Imagen urlImagen: list) {
            urlImagen.setProducto(producto);
        }

        return repositorioProducto.save(producto);

        /**
         * revisar si responde con un DTO JSON**/
    }

    public List<Producto> buscarProductos(){
        List<Producto> listProductos = repositorioProducto.findAll();
        if (!listProductos.isEmpty()){
            /**revisar si responde con un DTO JSON**/
            return listProductos;
        }
        else {
            throw new IllegalStateException("No existen productos registrados en el sistema");
        }

    }

    public Producto buscarProductoPorId(long id){
        Optional<Producto> producto = repositorioProducto.findById(id);
        if (producto.isPresent()){
            return producto.get();
            /**revisar si responde con un DTO JSON**/
        }
        else {
            throw new IllegalStateException("El producto no existe en el sistema");
        }

    }

    public String actualizarProducto(Producto producto){
        Producto productoActualizar = buscarProductoPorId(producto.getId());
        repositorioProducto.save(producto);
        return String.format("Producto %s actualizado correctamente", productoActualizar.getNombre());

    }

    public String eliminarProducto(long id){
        Producto productoEliminar = buscarProductoPorId(id);
        repositorioProducto.deleteById(id);
        return String.format("Producto %s eliminado correctamente", productoEliminar.getNombre());

        /**tener en cuenta la relación con la tabla de categoría**/

    }
}
