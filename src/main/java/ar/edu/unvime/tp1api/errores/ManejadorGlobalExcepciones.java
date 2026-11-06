package ar.edu.unvime.tp1api.errores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import ar.edu.unvime.tp1api.favoritos.FavoritoNoEncontradoException;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    @ExceptionHandler(FavoritoNoEncontradoException.class)
    public ResponseEntity<ErrorRespuesta> manejarFavoritoNoEncontrado(FavoritoNoEncontradoException ex) {
        ErrorRespuesta error = new ErrorRespuesta(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRespuesta> manejarValidacionFallida(MethodArgumentNotValidException ex) {
        List<String> detalles = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .toList();

        ErrorRespuesta error = new ErrorRespuesta(
                HttpStatus.BAD_REQUEST.value(),
                "Error de validacion",
                detalles
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorRespuesta> manejarErrorApiExterna(RestClientException ex) {
        ErrorRespuesta error = new ErrorRespuesta(
                HttpStatus.BAD_GATEWAY.value(),
                "No se pudo obtener informacion del servicio externo de productos",
                List.of(ex.getMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
    }
}
