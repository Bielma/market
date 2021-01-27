package com.bielma.market.persistence;

import com.bielma.market.persistence.crud.ProductoCRUDRepository;
import com.bielma.market.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Para indicar que esta clase interactua con la DB tambien @COmponent
@Repository
public class ProductoRepository {
    private ProductoCRUDRepository productoCRUDRepository;


    public List<Producto>  getAll(){
        return (List<Producto>) productoCRUDRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria){
        return productoCRUDRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);

    }

    public Optional<List<Producto>> getEscasos(int cantidad){
        return productoCRUDRepository.findByCantidadStockLessThanAndEstado(cantidad,true);
    }
    public Optional<Producto> getProdudcto(int idProducto){
        return productoCRUDRepository.findById(idProducto);
    }

    public Producto save(Producto producto){
        return productoCRUDRepository.save(producto);
    }

    public void delete(int idProducto){
        productoCRUDRepository.deleteById(idProducto);
    }


}
