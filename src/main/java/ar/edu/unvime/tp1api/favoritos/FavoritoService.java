package ar.edu.unvime.tp1api.favoritos;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unvime.tp1api.favoritos.dto.FavoritoEntradaDTO;
import ar.edu.unvime.tp1api.favoritos.dto.FavoritoSalidaDTO;

@Service
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;

    public FavoritoService(FavoritoRepository favoritoRepository) {
        this.favoritoRepository = favoritoRepository;
    }

    public List<FavoritoSalidaDTO> listarTodos() {
        return favoritoRepository.listarTodos().stream()
                .map(this::convertirASalida)
                .toList();
    }

    public FavoritoSalidaDTO buscarPorId(Long id) {
        Favorito favorito = favoritoRepository.buscarPorId(id)
                .orElseThrow(() -> new FavoritoNoEncontradoException(id));
        return convertirASalida(favorito);
    }

    public FavoritoSalidaDTO crear(FavoritoEntradaDTO entrada) {
        Favorito favorito = new Favorito(
                null,
                entrada.getProductoId(),
                entrada.getNota(),
                LocalDateTime.now()
        );
        Favorito guardado = favoritoRepository.guardar(favorito);
        return convertirASalida(guardado);
    }

    public FavoritoSalidaDTO actualizar(Long id, FavoritoEntradaDTO entrada) {
        Favorito existente = favoritoRepository.buscarPorId(id)
                .orElseThrow(() -> new FavoritoNoEncontradoException(id));

        existente.setProductoId(entrada.getProductoId());
        existente.setNota(entrada.getNota());

        Favorito actualizado = favoritoRepository.guardar(existente);
        return convertirASalida(actualizado);
    }

    public void eliminar(Long id) {
        favoritoRepository.buscarPorId(id)
                .orElseThrow(() -> new FavoritoNoEncontradoException(id));
        favoritoRepository.eliminar(id);
    }

    private FavoritoSalidaDTO convertirASalida(Favorito favorito) {
        return new FavoritoSalidaDTO(
                favorito.getId(),
                favorito.getProductoId(),
                favorito.getNota(),
                favorito.getFechaAgregado()
        );
    }
}