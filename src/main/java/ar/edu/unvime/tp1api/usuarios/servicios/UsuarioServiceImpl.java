package ar.edu.unvime.tp1api.usuarios.servicios;

import ar.edu.unvime.tp1api.usuarios.Usuario;
import ar.edu.unvime.tp1api.usuarios.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioServiceImpl {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarUsuario(String email, String password) {
        // Validar si ya existe un usuario con ese email
        Optional<Usuario> existente = usuarioRepository.buscarPorEmail(email);
        if (existente.isPresent()) {
            throw new RuntimeException("El email ya se encuentra registrado");
        }

        // Hashear la contraseña por seguridad
        String passwordHash = passwordEncoder.encode(password);
        
        // Crea el nuevo usuario con rol por defecto "USER"
        Usuario nuevoUsuario = new Usuario(null, email, passwordHash, "USER");

        return usuarioRepository.guardar(nuevoUsuario);
    }
}