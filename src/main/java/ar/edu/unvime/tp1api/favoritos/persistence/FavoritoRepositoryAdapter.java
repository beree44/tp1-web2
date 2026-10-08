package ar.edu.unvime.tp1api.favoritos.persistence;

import ar.edu.unvime.tp1api.favoritos.Favorito;
import ar.edu.unvime.tp1api.favoritos.FavoritoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FavoritoRepositoryAdapter implements FavoritoRepository {

    private final FavoritoJpaRepository jpaRepository;

    public FavoritoRepositoryAdapter(FavoritoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Favorito guardar(Favorito favorito) {
        FavoritoEntity entity = toEntity(favorito);
        FavoritoEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public List<Favorito> listarTodos() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Favorito> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);
    }

    private FavoritoEntity toEntity(Favorito favorito) {
        FavoritoEntity entity = new FavoritoEntity();
        entity.setId(favorito.getId());
        entity.setProductoId(favorito.getProductoId());
        entity.setNota(favorito.getNota());
        entity.setFechaAlta(favorito.getFechaAgregado()); 
        entity.setListaId(favorito.getListaId()); // <-- Mapeo de listaId agregado
        return entity;
    }

    private Favorito toDomain(FavoritoEntity entity) {
        Favorito favorito = new Favorito(
                entity.getId(),
                entity.getProductoId(),
                entity.getNota(),
                entity.getFechaAlta()
        );
        favorito.setListaId(entity.getListaId()); // <-- Mapeo de listaId agregado
        return favorito;
    }
}