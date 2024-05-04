package dev.guedes.hibernatedemo.repository.impl;

import dev.guedes.hibernatedemo.exception.DatabaseException;
import dev.guedes.hibernatedemo.model.Product;
import dev.guedes.hibernatedemo.repository.ProductRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author João Guedes
 */
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final SessionFactory sessionFactory;

    @Override
    public void save(Product product) throws DatabaseException {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (product.getId() == null) {
                session.persist(product);
            } else {
                session.merge(product);
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
    public Optional<Product> findById(Long id) throws DatabaseException {
        try (Session session = this.sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Product.class, id));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public List<Product> findAll() throws DatabaseException {
        try (Session session = this.sessionFactory.openSession()) {
            return session.createQuery("FROM products", Product.class).getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public List<Product> findAllByNameContaining(String name) throws DatabaseException {
        try (Session session = this.sessionFactory.openSession()) {
            return session.createNamedQuery("Product.findAllByNameContaining", Product.class)
                    .setParameter("name", name)
                    .getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public List<Product> findAllByNameStartingWithAndPriceBetween(String name, BigDecimal minPrice, BigDecimal maxPrice) throws DatabaseException {
        try (Session session = this.sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            Root<Product> root = query.from(Product.class);
            Predicate predicate = builder.and(
                    builder.like(root.get("name"), name.concat("%")),
                    builder.between(root.get("price"), minPrice, maxPrice)
            );
            query.select(root).where(predicate);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void delete(Product product) throws DatabaseException {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseException(e.getMessage());
        }
    }

}
