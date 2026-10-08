package ar.edu.unvime.tp1api.listas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListaService {

    private final ListaRepository listaRepository;

    public ListaService(ListaRepository listaRepository) {
        this.listaRepository = listaRepository;
    }

    public Lista guardar(Lista lista) {
        return listaRepository.guardar(lista);
    }

    public List<Lista> listarTodas() {
        return listaRepository.listarTodas();
    }

    public Optional<Lista> buscarPorId(Long id) {
        return listaRepository.buscarPorId(id);
    }

    public void eliminar(Long id) {
        listaRepository.eliminar(id);
    }
}