package ar.edu.unvime.tp1api.productos.external;

import java.util.List;

public class DummyRespuesta {

    private List<DummyProducto> products;
    private Integer total;
    private Integer skip;
    private Integer limit;

    public List<DummyProducto> getProducts() {
        return products;
    }

    public void setProducts(List<DummyProducto> products) {
        this.products = products;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}