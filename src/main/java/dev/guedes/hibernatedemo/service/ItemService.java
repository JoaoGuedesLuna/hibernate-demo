package dev.guedes.hibernatedemo.service;

import dev.guedes.hibernatedemo.exception.DatabaseException;
import dev.guedes.hibernatedemo.model.Item;
import dev.guedes.hibernatedemo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

/**
 * @author João Guedes
 */
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void save(Item item) throws DatabaseException {
        this.itemRepository.save(item);
    }

    public Optional<Item> findById(Long id) throws DatabaseException {
        return this.itemRepository.findById(id);
    }

    public void delete(Item item) throws DatabaseException {
        this.itemRepository.delete(item);
    }

}
