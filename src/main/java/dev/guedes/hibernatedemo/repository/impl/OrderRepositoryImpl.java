package dev.guedes.hibernatedemo.repository.impl;

import dev.guedes.hibernatedemo.exception.DatabaseException;
import dev.guedes.hibernatedemo.model.Order;
import dev.guedes.hibernatedemo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;

/**
 * @author João Guedes
 */
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final SessionFactory sessionFactory;

    @Override
    public void save(Order order) throws DatabaseException {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (order.getId() == null) {
                session.persist(order);
            } else {
                session.merge(order);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Optional<Order> findById(Long id) throws DatabaseException {
        try (Session session = this.sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Order.class, id));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public List<Order> findAllByAccountId(Long accountId) throws DatabaseException {
        try (Session session = this.sessionFactory.openSession()) {
            return session.createNamedQuery("Order.findAllByAccountId", Order.class)
                    .setParameter("accountId", accountId)
                    .getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void delete(Order order) throws DatabaseException {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseException(e.getMessage());
        }
    }

}
