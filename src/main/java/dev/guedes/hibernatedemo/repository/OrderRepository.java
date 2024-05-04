package dev.guedes.hibernatedemo.repository;

import dev.guedes.hibernatedemo.exception.DatabaseException;
import dev.guedes.hibernatedemo.model.Order;
import java.util.List;
import java.util.Optional;

/**
 * @author João Guedes
 */
public interface OrderRepository {

    void save(Order order) throws DatabaseException;

    Optional<Order> findById(Long id) throws DatabaseException;

    List<Order> findAllByAccountId(Long accountId) throws DatabaseException;

    void delete(Order order) throws DatabaseException;

}
