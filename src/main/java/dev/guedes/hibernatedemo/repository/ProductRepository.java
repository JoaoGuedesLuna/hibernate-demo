package dev.guedes.hibernatedemo.repository;

import dev.guedes.hibernatedemo.exception.DatabaseException;
import dev.guedes.hibernatedemo.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author João Guedes
 */
public interface ProductRepository {

    void save(Product product) throws DatabaseException;

    Optional<Product> findById(Long id) throws DatabaseException;

    List<Product> findAll() throws DatabaseException;

    List<Product> findAllByNameContaining(String name) throws DatabaseException;

    List<Product> findAllByNameStartingWithAndPriceBetween(String name, BigDecimal minPrice, BigDecimal maxPrice) throws DatabaseException;

    void delete(Product product) throws DatabaseException;

}
