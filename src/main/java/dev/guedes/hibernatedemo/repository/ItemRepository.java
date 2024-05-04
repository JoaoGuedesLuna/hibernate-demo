package dev.guedes.hibernatedemo.repository;

import dev.guedes.hibernatedemo.exception.DatabaseException;
import dev.guedes.hibernatedemo.model.Item;
import java.util.Optional;

/**
 * @author João Guedes
 */
public interface ItemRepository {

    void save(Item item) throws DatabaseException;

    Optional<Item> findById(Long id) throws DatabaseException;

    void delete(Item item) throws DatabaseException;

}
