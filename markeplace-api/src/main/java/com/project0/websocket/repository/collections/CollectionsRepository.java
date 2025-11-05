package com.project0.websocket.repository.collections;
import com.project0.websocket.model.NFTCollections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CollectionsRepository implements  CollectionsInter{
    @Autowired
    JdbcTemplate jdbcTemplate;



    public long count() {
        try {
            return jdbcTemplate.queryForObject("select count(*) from collections", Integer.class);
        }
        catch (NullPointerException e)
        {
            return 0;
        }
    }



    public NFTCollections save(NFTCollections collections) {
        int size=collections.getCollectionItems().size();
        List<Object[]> collectionsList = new ArrayList<>();
        String collectionsName=collections.getCollectionsName();
        String timestamp= collections.getTimestamp().toString();
        int useId=  collections.getUserId();
         for(int i=0;i<size;i++){
             String collectionId=collections.getCollectionItems().get(i);
             Object[]temp={timestamp,collectionsName,useId,collectionId};
             collectionsList.add(temp);
         }
         jdbcTemplate.batchUpdate(
                "insert into collections (timestamp,collectiosname,userid,collectionids) values(?,?,?,?)", collectionsList);
        return collections;

    }

    @Override
    public Optional<NFTCollections> findById(Long userId) {
        return Optional.empty();
    }

}
