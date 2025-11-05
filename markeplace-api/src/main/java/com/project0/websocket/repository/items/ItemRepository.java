package com.project0.websocket.repository.items;

import com.project0.websocket.model.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface ItemRepository {

    int count();

    int save(Item itm);

    int updateItemPrice(Item itm);

    public int updateItem(Item item);

    public List<Long> updateItems(List<Item> items);

    int deleteById(Long id);
    int deleteByItemsId(List<Long> itemIds);

    List<Item> findAll();

    List<Item> findByNameAndPrice(String name, BigDecimal price);

    Optional<Item> findById(Long id);

    String getNameById(Long id);

    List<String> getItemIds();

    List<Long> saveAll(List<Item> items);

    List<Item> findAllItems( List<String> itemIds);

}
