package com.proyecto.hihatrental.servicio;

import com.proyecto.hihatrental.dto.ProductoPeticionDTO;
import com.proyecto.hihatrental.dto.ProductoRespuestaDTO;
import com.proyecto.hihatrental.entidad.Categoria;
import com.proyecto.hihatrental.entidad.Imagen;
import com.proyecto.hihatrental.entidad.Producto;
import com.proyecto.hihatrental.repositorio.RepositorioImagen;
import com.proyecto.hihatrental.repositorio.RepositorioProducto;
import com.proyecto.hihatrental.repositorio.RespositorioCategoria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioProducto {


    //@Autowired
    private RepositorioProducto repositorioProducto;
    private RespositorioCategoria respositorioCategoria;
    private RepositorioImagen repositorioImagen;

    public ServicioProducto(RepositorioProducto repositorioProducto, RespositorioCategoria respositorioCategoria, RepositorioImagen repositorioImagen) {
        this.repositorioProducto = repositorioProducto;
        this.respositorioCategoria = respositorioCategoria;
        this.repositorioImagen = repositorioImagen;
    }

    public ProductoRespuestaDTO registrarProducto(ProductoPeticionDTO producto) {

        List<String> listImagenes = new ArrayList<>();

        boolean nombreExiste = repositorioProducto.existsByNombre(producto.getNombre());
        if (nombreExiste) {
            throw new IllegalArgumentException("Nombre de producto ya existe en la BD");
        }

        /**HASTA AQUÍ DEBE YA DE VENIR VALIDADO DESDE EL FRONT QUE LA CATEGORÍA EXISTE POR UNA LIST DE CATEGORÍA QUE DEBE DE ESCOGER EN EL FORM PARA CREAR EL PRODUCTO
         * SI LA CATEGORÍA NO EXISTE NO DEBE DE APARECER EN LA LISTA EN EL FORM
         * DE IGUAL FORMA SE HACE LA VALIDACIÓN DESDE EL BACK
         **/

        Optional<Categoria> categoria = respositorioCategoria.findById(producto.getIdCategoria());

        if (categoria.isPresent()) {
            Categoria categoriaBuscada = new Categoria(categoria.get().getId(), categoria.get().getNombre());
            Producto productoRegistrar = new Producto(producto.getNombre(), producto.getDescripcion(), producto.getImagenes(), categoriaBuscada);

            List<Imagen> list = productoRegistrar.getImagenes();

            if (list.size() > 5) {
                throw new IllegalArgumentException("No se pueden agregar más de 5 imagenes por producto.");
            }

            for (Imagen urlImagen : list) {
                urlImagen.setProducto(productoRegistrar);
            }

            Producto productoRegistrado = repositorioProducto.save(productoRegistrar);

            for (Imagen urlImagen : productoRegistrado.getImagenes()) {
                listImagenes.add(urlImagen.getUrl());
            }

            return new ProductoRespuestaDTO(productoRegistrado.getId(), productoRegistrado.getNombre(), productoRegistrado.getDescripcion(), productoRegistrado.getCategoria().getNombre(), listImagenes);

        } else {

            throw new IllegalArgumentException("Categoria no existe registrada en el sistema.");
        }
    }

    public List<ProductoRespuestaDTO> buscarProductos() {

        List<Producto> listProductos = repositorioProducto.findAll();
        List<ProductoRespuestaDTO> respuestaProductoDTOLista = new ArrayList<>();

        if (!listProductos.isEmpty()) {
            for (Producto producto : listProductos) {
                List<String> listaUrls = new ArrayList<>();
                for (Imagen urlImagen : producto.getImagenes()) {
                    listaUrls.add(urlImagen.getUrl());
                }
                respuestaProductoDTOLista.add(new ProductoRespuestaDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getCategoria().getNombre(), listaUrls));
            }
            return respuestaProductoDTOLista;
        } else {
            throw new IllegalStateException("No existen productos registrados en el sistema");
        }
    }

    public ProductoRespuestaDTO buscarProductoPorId(long id) {

        Optional<Producto> producto = repositorioProducto.findById(id);
        List<String> listImagenes = new ArrayList<>();
        if (producto.isPresent()) {
            for (Imagen urlImagen : producto.get().getImagenes()) {
                listImagenes.add(urlImagen.getUrl());
            }
            return new ProductoRespuestaDTO(producto.get().getId(), producto.get().getNombre(), producto.get().getDescripcion(), producto.get().getCategoria().getNombre(), listImagenes);
        } else {
            throw new IllegalStateException("El producto no existe en el sistema");
        }

    }

    public String actualizarProducto(long id, ProductoPeticionDTO producto) {

        Optional<Categoria> categoriaEncontrada = respositorioCategoria.findById(producto.getIdCategoria());

        if (categoriaEncontrada.isPresent()) {
            Categoria categoria = new Categoria(categoriaEncontrada.get().getId(), categoriaEncontrada.get().getNombre());

            List<Imagen> listaImagenesEditar = producto.getImagenes();

            if (listaImagenesEditar.size() > 5) {
                throw new IllegalArgumentException("No se pueden agregar más de 5 imagenes por producto.");
            }

            List<Imagen> listaImagenesRegistradas = repositorioImagen.findByProductoId(id);

            if (listaImagenesEditar.size() > listaImagenesRegistradas.size()) {
                siListaImagenesEditarEsMayorQueListaImagenesRegistradas(id, producto, categoria, listaImagenesEditar, listaImagenesRegistradas);
            } else if (listaImagenesEditar.size() < listaImagenesRegistradas.size()) {
                siListaImagenesEditarEsMenorQueListaImagenesRegistradas(id, producto, categoria, listaImagenesEditar, listaImagenesRegistradas);
            }
            else {
                siListaImagenesEditarEsIgualQueListaImagenesRegistradas(id, producto, categoria, listaImagenesEditar, listaImagenesRegistradas);
            }

            return String.format("Producto %s actualizado correctamente", producto.getNombre());

        } else {

            throw new IllegalArgumentException("Categoria no existe registrada en el sistema.");
        }
    }

    public String eliminarProducto(long id) {
        ProductoRespuestaDTO productoEliminar = buscarProductoPorId(id);
        repositorioProducto.deleteById(id);
        return String.format("Producto %s eliminado correctamente", productoEliminar.getNombre());
    }

    private void siListaImagenesEditarEsMayorQueListaImagenesRegistradas(long id, ProductoPeticionDTO producto, Categoria categoria, List<Imagen> listaImagenesEditar, List<Imagen> listaImagenesRegistradas) {
        for (int i = 0; i < listaImagenesRegistradas.size(); i++) {
            listaImagenesRegistradas.get(i).setUrl(listaImagenesEditar.get(i).getUrl());
        }

        Producto productoActualizar = new Producto(id, producto.getNombre(), producto.getDescripcion(), producto.getImagenes(), categoria);

        listaImagenesEditar.subList(0, listaImagenesRegistradas.size()).clear();
        for (Imagen urlImagen : listaImagenesEditar) {
            urlImagen.setProducto(productoActualizar);
        }

        repositorioProducto.save(productoActualizar);
    }

    private void siListaImagenesEditarEsMenorQueListaImagenesRegistradas(long id, ProductoPeticionDTO producto, Categoria categoria, List<Imagen> listaImagenesEditar, List<Imagen> listaImagenesRegistradas) {
        for (int i = 0; i < listaImagenesEditar.size(); i++) {
            listaImagenesRegistradas.get(i).setUrl(listaImagenesEditar.get(i).getUrl());
        }

        listaImagenesRegistradas.subList(0, listaImagenesEditar.size()).clear();
        for (int i = 0; i < listaImagenesRegistradas.size(); i++) {
            repositorioImagen.deleteById(listaImagenesRegistradas.get(i).getId());
        }

        Producto productoActualizar = new Producto(id, producto.getNombre(), producto.getDescripcion(), categoria);
        repositorioProducto.save(productoActualizar);
    }

    private void siListaImagenesEditarEsIgualQueListaImagenesRegistradas(long id, ProductoPeticionDTO producto, Categoria categoria, List<Imagen> listaImagenesEditar, List<Imagen> listaImagenesRegistradas) {
        for (int i = 0; i < listaImagenesRegistradas.size(); i++) {
            listaImagenesRegistradas.get(i).setUrl(listaImagenesEditar.get(i).getUrl());
        }

        Producto productoActualizar = new Producto(id, producto.getNombre(), producto.getDescripcion(), categoria);
        repositorioProducto.save(productoActualizar);
    }

}



