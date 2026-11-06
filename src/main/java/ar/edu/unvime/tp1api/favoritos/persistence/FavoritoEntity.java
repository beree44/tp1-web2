package ar.edu.unvime.tp1api.favoritos.persistence;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "favoritos")
public class FavoritoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    private String nota;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDateTime fechaAlta;

    @Column(name = "lista_id")
    private Long listaId;

    public FavoritoEntity() {
    }

    public FavoritoEntity(Long id, Long productoId, String nota, LocalDateTime fechaAlta) {
        this.id = id;
        this.productoId = productoId;
        this.nota = nota;
        this.fechaAlta = fechaAlta;
    }

    public FavoritoEntity(Long id, Long productoId, String nota, LocalDateTime fechaAlta, Long listaId) {
        this.id = id;
        this.productoId = productoId;
        this.nota = nota;
        this.fechaAlta = fechaAlta;
        this.listaId = listaId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }

    public String getNota() { return nota; }
    public void setNota(String nota) { this.nota = nota; }

    public LocalDateTime getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDateTime fechaAlta) { this.fechaAlta = fechaAlta; }

    public Long getListaId() { return listaId; }
    public void setListaId(Long listaId) { this.listaId = listaId; }
}