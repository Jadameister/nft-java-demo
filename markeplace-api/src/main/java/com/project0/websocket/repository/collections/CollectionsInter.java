package com.project0.websocket.repository.collections;
import com.project0.websocket.model.NFTCollections;
import java.util.Optional;

public interface CollectionsInter {
    long count();
    NFTCollections save(NFTCollections collection);

    Optional<NFTCollections> findById(Long userId);

}
