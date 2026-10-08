package ar.edu.unvime.tp1api.listas;

import java.util.List;
import java.util.Optional;

public interface ListaRepository {
    Lista guardar(Lista lista);
    List<Lista> listarTodas();
    Optional<Lista> buscarPorId(Long id);
    void eliminar(Long id);
}