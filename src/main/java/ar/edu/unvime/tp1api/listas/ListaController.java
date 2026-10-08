package ar.edu.unvime.tp1api.listas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listas")
public class ListaController {

    private final ListaService listaService;

    public ListaController(ListaService listaService) {
        this.listaService = listaService;
    }

    @PostMapping
    public ResponseEntity<Lista> crear(@RequestBody Lista lista) {
        Lista nuevaLista = listaService.guardar(lista);
        return new ResponseEntity<>(nuevaLista, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Lista>> listarTodas() {
        List<Lista> listas = listaService.listarTodas();
        return ResponseEntity.ok(listas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lista> buscarPorId(@PathVariable Long id) {
        return listaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (listaService.buscarPorId(id).isPresent()) {
            listaService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}