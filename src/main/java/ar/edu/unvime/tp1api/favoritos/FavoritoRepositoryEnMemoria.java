package ar.edu.unvime.tp1api.favoritos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

@Repository
public class FavoritoRepositoryEnMemoria implements FavoritoRepository {

    private final List<Favorito> favoritos = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    @Override
    public List<Favorito> listarTodos() {
        return new ArrayList<>(favoritos);
    }

    @Override
    public Optional<Favorito> buscarPorId(Long id) {
        return favoritos.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst();
    }

    @Override
    public Favorito guardar(Favorito favorito) {
        if (favorito.getId() == null) {
            favorito.setId(contadorId.getAndIncrement());
            favoritos.add(favorito);
        } else {
            eliminar(favorito.getId());
            favoritos.add(favorito);
        }
        return favorito;
    }

    @Override
    public void eliminar(Long id) {
        favoritos.removeIf(f -> f.getId().equals(id));
    }
}