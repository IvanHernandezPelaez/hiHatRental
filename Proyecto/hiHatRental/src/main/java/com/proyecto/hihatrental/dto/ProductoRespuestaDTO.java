package com.proyecto.hihatrental.dto;

/**import com.proyecto.hihatrental.entidad.Imagen;*/
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductoRespuestaDTO {
    private long id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private List<String> imagenes;

    /**Esto si falla la app toca descomentarearlo y probar
    public void setImagenes(List<Imagen> imagenes) {
    }
     */
}
