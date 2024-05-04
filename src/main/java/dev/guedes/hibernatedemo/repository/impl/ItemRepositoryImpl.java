package dev.guedes.hibernatedemo.repository.impl;

import dev.guedes.hibernatedemo.exception.DatabaseException;
import dev.guedes.hibernatedemo.model.Item;
import dev.guedes.hibernatedemo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Optional;

/**
 * @author João Guedes
 */
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final SessionFactory sessionFactory;

    @Override
    public void save(Item item) throws DatabaseException {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (item.getId() == null) {
                session.persist(item);
            } else {
                session.merge(item);
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
    public Optional<Item> findById(Long id) throws DatabaseException {
        try (Session session = this.sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Item.class, id));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void delete(Item item) throws DatabaseException {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseException(e.getMessage());
        }
    }

}
