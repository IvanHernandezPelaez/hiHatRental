package com.proyecto.hihatrental.dto;

public class RespuestaExepcionDTO {
    private String mensaje;

    public RespuestaExepcionDTO(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
