package com.bielma.market.persistence;

import com.bielma.market.domain.Product;
import com.bielma.market.persistence.crud.ProductoCRUDRepository;
import com.bielma.market.persistence.entity.Producto;
import com.bielma.market.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Para indicar que esta clase interactua con la DB tambien @COmponent
@Repository
public class ProductoRepository implements com.bielma.market.domain.repository.ProductoRepository {
    private ProductoCRUDRepository productoCRUDRepository;
    private ProductMapper mapper;

    @Override
    public List<Product>  getAll(){
        List<Producto> productos = (List<Producto>) productoCRUDRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCRUDRepository.findByIdCategoriaOrderByNombreAsc( categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCRUDRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCRUDRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCRUDRepository.save(producto));
    }

    @Override
    public void delete(int productoId){
        productoCRUDRepository.deleteById(productoId);
    }


}
