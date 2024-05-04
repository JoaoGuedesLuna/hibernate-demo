package dev.guedes.hibernatedemo.service;

import dev.guedes.hibernatedemo.database.Database;
import dev.guedes.hibernatedemo.exception.DatabaseException;
import dev.guedes.hibernatedemo.repository.AccountRepository;
import dev.guedes.hibernatedemo.repository.ItemRepository;
import dev.guedes.hibernatedemo.repository.OrderRepository;
import dev.guedes.hibernatedemo.repository.ProductRepository;
import dev.guedes.hibernatedemo.repository.impl.AccountRepositoryImpl;
import dev.guedes.hibernatedemo.repository.impl.ItemRepositoryImpl;
import dev.guedes.hibernatedemo.repository.impl.OrderRepositoryImpl;
import dev.guedes.hibernatedemo.repository.impl.ProductRepositoryImpl;

/**
 * @author João Guedes
 */
public class ServiceFactory {

    public static AccountService createAccountService() throws DatabaseException {
        AccountRepository accountRepository = new AccountRepositoryImpl(Database.getSessionFactory());
        return new AccountService(accountRepository);
    }

    public static ProductService createProductService() throws DatabaseException {
        ProductRepository productRepository = new ProductRepositoryImpl(Database.getSessionFactory());
        return new ProductService(productRepository);
    }

    public static OrderService createOrderService() throws DatabaseException {
        OrderRepository orderRepository = new OrderRepositoryImpl(Database.getSessionFactory());
        return new OrderService(orderRepository);
    }

    public static ItemService createItemService() throws DatabaseException {
        ItemRepository itemRepository = new ItemRepositoryImpl(Database.getSessionFactory());
        return new ItemService(itemRepository);
    }

}
