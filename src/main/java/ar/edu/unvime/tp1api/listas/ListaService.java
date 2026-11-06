package ar.edu.unvime.tp1api.listas;

import ar.edu.unvime.tp1api.usuarios.Usuario;
import ar.edu.unvime.tp1api.usuarios.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ListaService {

    private final ListaRepository listaRepository;
    private final UsuarioRepository usuarioRepository;

    public ListaService(ListaRepository listaRepository, UsuarioRepository usuarioRepository) {
        this.listaRepository = listaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Lista guardar(Lista lista) {
        // 1. Email del usuario autenticado desde el token JWT
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        // 2. Buscamos al usuario en la base de datos para obtener su ID real
        Usuario usuario = usuarioRepository.buscarPorEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 3. Asignamos automáticamente el ID del usuario autenticado a la lista
        lista.setUsuarioId(usuario.getId());

        // 4. Guardamos la lista vinculada al usuario
        return listaRepository.guardar(lista);
    }

    public List<Lista> listarTodas() {
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.buscarPorEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Si es ADMIN, ve todas las listas. Si es USER, solo ve las suyas.
        if ("ADMIN".equals(usuario.getRol())) {
            return listaRepository.listarTodas();
        }

        return listaRepository.listarTodas().stream()
                .filter(l -> l.getUsuarioId().equals(usuario.getId()))
                .toList();
    }

    public Optional<Lista> buscarPorId(Long id) {
        Lista lista = listaRepository.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista no encontrada"));

        validarPropietarioOAdmin(lista);

        return Optional.of(lista);
    }

    public void eliminar(Long id) {
        Lista lista = listaRepository.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista no encontrada"));

        validarPropietarioOAdmin(lista);

        listaRepository.eliminar(id);
    }

    // Método auxiliar para validar si el usuario es dueño de la lista o es ADMIN (Retorna 403 Forbidden si no tiene permisos)
    private void validarPropietarioOAdmin(Lista lista) {
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.buscarPorEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if ("ADMIN".equals(usuario.getRol())) {
            return; // El administrador puede acceder a todo
        }

        if (!lista.getUsuarioId().equals(usuario.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes permisos para acceder o modificar esta lista");
        }
    }
}