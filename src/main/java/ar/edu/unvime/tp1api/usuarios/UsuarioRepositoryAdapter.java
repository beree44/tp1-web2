package ar.edu.unvime.tp1api.usuarios;

import ar.edu.unvime.tp1api.usuarios.Usuario;
import ar.edu.unvime.tp1api.usuarios.UsuarioRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UsuarioRepositoryAdapter implements UsuarioRepository {

    private final UsuarioJpaRepository jpaRepository;

    public UsuarioRepositoryAdapter(UsuarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity(usuario.getEmail(), usuario.getPasswordHash(), usuario.getRol());
        if (usuario.getId() != null) {
            entity.setId(usuario.getId());
        }
        UsuarioEntity guardada = jpaRepository.save(entity);
        return new Usuario(guardada.getId(), guardada.getEmail(), guardada.getPasswordHash(), guardada.getRol());
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(e -> new Usuario(e.getId(), e.getEmail(), e.getPasswordHash(), e.getRol()));
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(e -> new Usuario(e.getId(), e.getEmail(), e.getPasswordHash(), e.getRol()));
    }
}