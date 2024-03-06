package com.proyecto.hihatrental.servicio;

import com.proyecto.hihatrental.dto.RespuestaProductoDTO;
import com.proyecto.hihatrental.entidad.Imagen;
import com.proyecto.hihatrental.entidad.Producto;
import com.proyecto.hihatrental.repositorio.RepositorioProducto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioProducto {


    //@Autowired
    private RepositorioProducto repositorioProducto;

    public ServicioProducto(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }

    public RespuestaProductoDTO registrarProducto(Producto producto){

        List<String> listImagenes = new ArrayList<>();

        boolean nombreExiste = repositorioProducto.existsByNombre(producto.getNombre());
        if (nombreExiste){
            throw new IllegalArgumentException("Nombre de producto ya existe en la BD");
        }

        List<Imagen> list = producto.getImagenes();

        for (Imagen urlImagen: list) {
            urlImagen.setProducto(producto);
        }

        Producto productoRegistrado = repositorioProducto.save(producto);

        for (Imagen urlImagen: productoRegistrado.getImagenes()) {
            listImagenes.add(urlImagen.getUrl());
        }

        return new RespuestaProductoDTO(productoRegistrado.getNombre(), productoRegistrado.getDescripcion(), listImagenes);
    }

    public List<RespuestaProductoDTO> buscarProductos(){

        List<Producto> listProductos = repositorioProducto.findAll();
        List<RespuestaProductoDTO> respuestaProductoDTOList = new ArrayList<>();

        if (!listProductos.isEmpty()){
            for (Producto producto: listProductos) {
                List<String> listaUrls = new ArrayList<>();
                for (Imagen urlImagen: producto.getImagenes()) {
                    listaUrls.add(urlImagen.getUrl());
                }
                respuestaProductoDTOList.add(new RespuestaProductoDTO(producto.getNombre(), producto.getDescripcion(), listaUrls));
            }
            return respuestaProductoDTOList;
        }
        else {
            throw new IllegalStateException("No existen productos registrados en el sistema");
        }
    }

    public RespuestaProductoDTO buscarProductoPorId(long id){
        Optional<Producto> producto = repositorioProducto.findById(id);
        List<String> listImagenes = new ArrayList<>();
        if (producto.isPresent()){
            for (Imagen urlImagen: producto.get().getImagenes()) {
                listImagenes.add(urlImagen.getUrl());
            }
            return new RespuestaProductoDTO(producto.get().getNombre(), producto.get().getDescripcion(), listImagenes);
        }
        else {
            throw new IllegalStateException("El producto no existe en el sistema");
        }

    }

    public String actualizarProducto(Producto producto){
        RespuestaProductoDTO productoActualizar = buscarProductoPorId(producto.getId());
        repositorioProducto.save(producto);
        return String.format("Producto %s actualizado correctamente", productoActualizar.getNombre());

    }

    public String eliminarProducto(long id){
        RespuestaProductoDTO productoEliminar = buscarProductoPorId(id);
        repositorioProducto.deleteById(id);
        return String.format("Producto %s eliminado correctamente", productoEliminar.getNombre());

        /**tener en cuenta la relación con la tabla de categoría**/

    }
}
