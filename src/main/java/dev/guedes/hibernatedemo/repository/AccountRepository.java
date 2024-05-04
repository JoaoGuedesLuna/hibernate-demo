package dev.guedes.hibernatedemo.repository;

import dev.guedes.hibernatedemo.exception.DatabaseException;
import dev.guedes.hibernatedemo.model.Account;
import java.util.Optional;

/**
 * @author João Guedes
 */
public interface AccountRepository {

    void save(Account account) throws DatabaseException;

    Optional<Account> findById(Long id) throws DatabaseException;

    Optional<Account> findByEmail(String email) throws DatabaseException;

    void delete(Account account) throws DatabaseException;

}
