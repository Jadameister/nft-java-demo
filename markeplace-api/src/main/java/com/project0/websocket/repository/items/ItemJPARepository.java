package com.project0.websocket.repository.items;

import com.project0.websocket.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ItemJPARepository extends JpaRepository<Item, Long> {

     Optional<Item> findById(Long id);


}
