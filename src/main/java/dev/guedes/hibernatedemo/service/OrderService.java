package dev.guedes.hibernatedemo.service;

import dev.guedes.hibernatedemo.exception.DatabaseException;
import dev.guedes.hibernatedemo.model.Order;
import dev.guedes.hibernatedemo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author João Guedes
 */
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void save(Order order) throws DatabaseException {
        this.orderRepository.save(order);
    }

    public Optional<Order> findById(Long id) throws DatabaseException {
        return this.orderRepository.findById(id);
    }

    public List<Order> findAllByAccountId(Long accountId) throws DatabaseException {
        return this.orderRepository.findAllByAccountId(accountId);
    }

    public void delete(Order order) throws DatabaseException {
        this.orderRepository.delete(order);
    }

    public BigDecimal orderTotalPrice(Order order) {
        return order.getItems().stream()
                .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
