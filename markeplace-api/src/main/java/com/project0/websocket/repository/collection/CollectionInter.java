package com.project0.websocket.repository.collection;
import com.project0.websocket.model.Collection;

import java.sql.SQLException;
import java.util.List;

public interface CollectionInter {

    int count();
    int[] save(Collection collection);
     int updateCollection(Collection collection) throws SQLException;
    int deleteById(Long id);
     int deleteByCollectionIds(List<Long> collectionIds);
    int deleteByCollectionItemIds(List<Long> ids);
    List<Collection> findById(Long userId);

}
