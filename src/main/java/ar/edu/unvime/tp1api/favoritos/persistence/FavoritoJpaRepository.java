package ar.edu.unvime.tp1api.favoritos.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritoJpaRepository extends JpaRepository<FavoritoEntity, Long> {
}