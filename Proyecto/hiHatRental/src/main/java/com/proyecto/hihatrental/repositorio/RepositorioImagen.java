package com.proyecto.hihatrental.repositorio;

import com.proyecto.hihatrental.entidad.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepositorioImagen extends JpaRepository<Imagen, Long> {

    List<Imagen> findByProductoId(Long Long);


}
