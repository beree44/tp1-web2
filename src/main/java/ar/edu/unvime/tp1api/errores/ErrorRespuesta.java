package ar.edu.unvime.tp1api.errores;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorRespuesta {

    private LocalDateTime timestamp;
    private int status;
    private String mensaje;
    private List<String> detalles;

    public ErrorRespuesta(int status, String mensaje, List<String> detalles) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public List<String> getDetalles() {
        return detalles;
    }
}
