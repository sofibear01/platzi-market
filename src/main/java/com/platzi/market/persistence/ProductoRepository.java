package com.platzi.market.persistence;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.Producto;
import com.platzi.market.persistence.mapper.ProductMapper;
//<<<<<<< HEAD
//=======
import org.springframework.beans.factory.ObjectProvider;
//>>>>>>> 8a6b33fca3538b311d937c9b9131f260b6e8bec3
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//<<<<<<< HEAD
public class ProductoRepository implements ProductRepository{
    @Autowired  //agregue esto
    private ProductoCrudRepository productoCrudRepository;
    @Autowired  // y esto tambien agregue jutno con la linea de abajo
    private ProductMapper mapper; //esta agregada
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
        //inicio cambios del otro commit =======
//public class ProductoRepository implements ProductRepository {
        //  @Autowired
        //private ProductoCrudRepository productoCrudRepository;
        //@Autowired
        //private ProductMapper mapper;

        //@Override
        //public List<Product> getAll(){
        //    List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        //    return mapper.toProducts(productos);
// fin cambios del otro commit >>>>>>> 8a6b33fca3538b311d937c9b9131f260b6e8bec3
    }
    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}
