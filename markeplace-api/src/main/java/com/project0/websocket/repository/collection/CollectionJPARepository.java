package com.project0.websocket.repository.collection;

import java.util.Optional;

import com.project0.websocket.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CollectionJPARepository extends JpaRepository<Collection, Long> {
//    Page<Item> findById(long id, Pageable pageable);
//    Page<Item> findByTitleContaining(String title, Pageable pageable)
    Optional<Collection> findById(Long id);

}
