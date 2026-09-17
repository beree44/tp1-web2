package ar.edu.unvime.tp1api.favoritos;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unvime.tp1api.favoritos.dto.FavoritoEntradaDTO;
import ar.edu.unvime.tp1api.favoritos.dto.FavoritoSalidaDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crea un nuevo favorito")
    public FavoritoSalidaDTO crear(@Valid @RequestBody FavoritoEntradaDTO entrada) {
        return favoritoService.crear(entrada);
    }

    @GetMapping
    @Operation(summary = "Lista todos los favoritos guardados")
    public List<FavoritoSalidaDTO> listar() {
        return favoritoService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un favorito puntual por su id")
    public FavoritoSalidaDTO obtenerUno(@PathVariable Long id) {
        return favoritoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un favorito existente")
    public FavoritoSalidaDTO actualizar(@PathVariable Long id, @Valid @RequestBody FavoritoEntradaDTO entrada) {
        return favoritoService.actualizar(id, entrada);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Elimina un favorito por su id")
    public void eliminar(@PathVariable Long id) {
        favoritoService.eliminar(id);
    }
}