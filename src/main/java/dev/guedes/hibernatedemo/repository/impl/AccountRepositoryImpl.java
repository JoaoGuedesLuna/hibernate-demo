package dev.guedes.hibernatedemo.repository.impl;

import dev.guedes.hibernatedemo.exception.DatabaseException;
import dev.guedes.hibernatedemo.model.Account;
import dev.guedes.hibernatedemo.repository.AccountRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Optional;

/**
 * @author João Guedes
 */
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final SessionFactory sessionFactory;

    @Override
    public void save(Account account) throws DatabaseException {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (account.getId() == null) {
                session.persist(account);
            } else {
                session.merge(account);
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
    public Optional<Account> findById(Long id) throws DatabaseException {
        try (Session session = this.sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Account.class, id));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Optional<Account> findByEmail(String email) throws DatabaseException {
        try (Session session = this.sessionFactory.openSession()) {
            return Optional.ofNullable(session
                    .createNamedQuery("Account.findByEmail", Account.class)
                    .setParameter("email", email)
                    .getSingleResult()
            );
        } catch (NoResultException nre) {
            return Optional.empty();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void delete(Account account) throws DatabaseException {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(account);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseException(e.getMessage());
        }
    }

}
