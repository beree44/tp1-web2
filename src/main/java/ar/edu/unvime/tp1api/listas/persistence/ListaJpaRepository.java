package ar.edu.unvime.tp1api.listas.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaJpaRepository extends JpaRepository<ListaEntity, Long> {
}
