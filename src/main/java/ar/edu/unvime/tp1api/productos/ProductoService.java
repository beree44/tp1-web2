package ar.edu.unvime.tp1api.productos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import ar.edu.unvime.tp1api.productos.dto.ProductoDTO;
import ar.edu.unvime.tp1api.productos.external.DummyProducto;
import ar.edu.unvime.tp1api.productos.external.DummyRespuesta;

@Service
public class ProductoService {

    private final RestClient restClient;

    public ProductoService() {
        this.restClient = RestClient.create("https://dummyjson.com");
    }

    public List<ProductoDTO> listarTodos() {
        DummyRespuesta respuesta = restClient.get()
                .uri("/products")
                .retrieve()
                .body(DummyRespuesta.class);

        return respuesta.getProducts().stream()
                .map(this::convertirADto)
                .toList();
    }

    public ProductoDTO buscarPorId(Long id) {
        DummyProducto producto = restClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .body(DummyProducto.class);

        return convertirADto(producto);
    }

    private ProductoDTO convertirADto(DummyProducto dummy) {
        return new ProductoDTO(
                dummy.getId(),
                dummy.getTitle(),
                dummy.getDescription(),
                dummy.getPrice(),
                dummy.getThumbnail()
        );
    }
}