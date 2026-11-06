package ar.edu.unvime.tp1api.usuarios;

import java.util.Optional;

public interface UsuarioRepository {
    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorEmail(String email);
    Optional<Usuario> buscarPorId(Long id);
}