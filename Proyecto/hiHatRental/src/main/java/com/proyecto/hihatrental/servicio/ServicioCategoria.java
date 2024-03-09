package com.proyecto.hihatrental.servicio;

import com.proyecto.hihatrental.dto.CategoriaRespuestaDTO;
import com.proyecto.hihatrental.entidad.Categoria;
import com.proyecto.hihatrental.repositorio.RespositorioCategoria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioCategoria {

    //@Autowired
    private RespositorioCategoria respositorioCategoria;

    public ServicioCategoria(RespositorioCategoria respositorioCategoria) {
        this.respositorioCategoria = respositorioCategoria;
    }

    public CategoriaRespuestaDTO registrarCategoria(Categoria categoria) {
        boolean existeCategoria = respositorioCategoria.existsByNombre(categoria.getNombre());
        if (existeCategoria) {
            throw new IllegalArgumentException("Categoria ya existe registrada en el sistema");
        }

        Categoria categoriaRegistrada = respositorioCategoria.save(categoria);

        return new CategoriaRespuestaDTO(categoriaRegistrada.getId(), categoriaRegistrada.getNombre());

    }

    public List<CategoriaRespuestaDTO> buscarCategorias() {
        List<Categoria> categorias = respositorioCategoria.findAll();
        if (!categorias.isEmpty()) {
            List<CategoriaRespuestaDTO> categoriaRespuestaDTOS = new ArrayList<>();
            for (Categoria categoria : categorias) {
                categoriaRespuestaDTOS.add(new CategoriaRespuestaDTO(categoria.getId(), categoria.getNombre()));
            }
            return categoriaRespuestaDTOS;
        } else {
            throw new IllegalStateException("No existen categorías registradas en el sistema");
        }
    }

    public CategoriaRespuestaDTO buscarCategoriaPorId(Long id) {
        Optional<Categoria> categoria = respositorioCategoria.findById(id);
        if (categoria.isPresent()) {
            return new CategoriaRespuestaDTO(categoria.get().getId(), categoria.get().getNombre());
        } else {
            throw new IllegalStateException("Categoria no existe en el sistema");
        }
    }

    public String actualizarCategoria(Categoria categoria) {
        Optional<Categoria> categoriaExiste = respositorioCategoria.findById(categoria.getId());
        if (categoriaExiste.isPresent()) {
            respositorioCategoria.save(categoria);
            return "Categoría actualizada correctamente";
        } else {
            throw new IllegalStateException("Categoria no existe en el sistema");
        }

    }

    public String eliminarCategoria(Long id, String confirmacion) {

        CategoriaRespuestaDTO categoriaRespuestaDTO = buscarCategoriaPorId(id);

        if (confirmacion.equals("Si")) {
            respositorioCategoria.deleteById(id);
            return String.format("Categoria %s eliminada correctamente", categoriaRespuestaDTO.getNombre());

        } else if(confirmacion.equals("No")){
            return "Operación cancelada";
        }

        return """
                Al eliminar la categoría afectará a todos los productos que pertenecen a esa categoría y se eliminarán también.
                Recomendación: cambiar de categorías a los productos y después eliminar la categoría que desea!
                ¿Desea eliminar la categoría?"
                Digite Si o No" 
                """;
    }
}
