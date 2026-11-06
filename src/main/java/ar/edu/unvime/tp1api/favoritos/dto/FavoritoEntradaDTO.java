package ar.edu.unvime.tp1api.favoritos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FavoritoEntradaDTO {

    @NotNull(message = "El productoId es obligatorio")
    private Long productoId;

    @NotBlank(message = "La nota no puede estar vacia")
    private String nota;

    public FavoritoEntradaDTO() {
    }

    public FavoritoEntradaDTO(Long productoId, String nota) {
        this.productoId = productoId;
        this.nota = nota;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}