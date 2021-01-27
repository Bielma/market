package com.bielma.market.persistence.crud;

import com.bielma.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


//Crudrepository recibe el modelo y el tipo de su llave primaraia
public interface ProductoCRUDRepository extends CrudRepository<Producto, Integer> {
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategia);
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int stock, boolean estado);


}
