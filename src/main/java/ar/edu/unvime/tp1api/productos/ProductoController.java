package ar.edu.unvime.tp1api.productos;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unvime.tp1api.productos.dto.ProductoDTO;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @Operation(summary = "Lista todos los productos del catalogo externo")
    public List<ProductoDTO> listar() {
        return productoService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un producto puntual por su id")
    public ProductoDTO obtenerUno(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }
}