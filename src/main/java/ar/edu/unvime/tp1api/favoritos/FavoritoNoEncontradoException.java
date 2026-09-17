package ar.edu.unvime.tp1api.favoritos;

public class FavoritoNoEncontradoException extends RuntimeException {

    public FavoritoNoEncontradoException(Long id) {
        super("No se encontró el favorito con id " + id);
    }
}