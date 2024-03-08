package com.proyecto.hihatrental.servicio;

import com.proyecto.hihatrental.dto.CategoriaRespuestaDTO;
import com.proyecto.hihatrental.entidad.Categoria;
import com.proyecto.hihatrental.repositorio.RespositorioCategoria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioCategoria {

    //@Autowired
    private RespositorioCategoria respositorioCategoria;
    public ServicioCategoria(RespositorioCategoria respositorioCategoria) {
        this.respositorioCategoria = respositorioCategoria;
    }

    public CategoriaRespuestaDTO registrarCategoria(Categoria categoria){
        boolean existeCategoria = respositorioCategoria.existsByNombre(categoria.getNombre());
        if (existeCategoria){
            throw new IllegalArgumentException("Categoria ya existe registrada en el sistema");
        }

        Categoria categoriaRegistrada = respositorioCategoria.save(categoria);

        return new CategoriaRespuestaDTO(categoriaRegistrada.getId(), categoriaRegistrada.getNombre());

    }

    public List<Categoria> buscarCategorias(){
        List<Categoria> categorias = respositorioCategoria.findAll();
        if (!categorias.isEmpty()){
            return categorias;
        }
        else {
            throw new IllegalStateException("No existen categorías registradas en el sistema");
        }
    }
}
