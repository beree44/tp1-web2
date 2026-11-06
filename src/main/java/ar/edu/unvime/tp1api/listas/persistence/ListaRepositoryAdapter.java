package ar.edu.unvime.tp1api.listas.persistence;

import ar.edu.unvime.tp1api.listas.Lista;
import ar.edu.unvime.tp1api.listas.ListaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ListaRepositoryAdapter implements ListaRepository {

    private final ListaJpaRepository jpaRepository;

    public ListaRepositoryAdapter(ListaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Lista guardar(Lista lista) {
        ListaEntity entity = toEntity(lista);
        ListaEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public List<Lista> listarTodas() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Lista> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);
    }

    private ListaEntity toEntity(Lista lista) {
        ListaEntity entity = new ListaEntity();
        entity.setId(lista.getId());
        entity.setNombre(lista.getNombre());
        entity.setDescripcion(lista.getDescripcion());
        entity.setUsuarioId(lista.getUsuarioId()); // Se incluye el usuarioId
        return entity;
    }

    private Lista toDomain(ListaEntity entity) {
        return new Lista(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getUsuarioId() // Se pasa el cuarto parámetro al dominio
        );
    }
}