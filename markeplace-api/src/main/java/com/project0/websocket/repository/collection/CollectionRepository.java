package com.project0.websocket.repository.collection;

import com.project0.websocket.model.Collection;
import com.project0.websocket.model.Item;
import com.project0.websocket.rands.RandomUUID;
import com.project0.websocket.repository.items.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


@Repository
public class CollectionRepository implements CollectionInter {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ItemsRepository itemsRepository;


//    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
//    {
//        Map<Object, Boolean> map = new ConcurrentHashMap<>();
//        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
//    }

    public int count() {
        try {
            return jdbcTemplate.queryForObject("select count(*) from collection", Integer.class);
        }
        catch (NullPointerException e)
        {
            return 0;
        }
    }

    public int[] save(Collection collection) {

        List<Object[]> collectionList = new ArrayList<>();
        String collectionId = RandomUUID.generateRandomItemId();
        String timestamp = collection.getTimestamp().toString();
        String collectionName = collection.getCollectionName();
        String ownerName = collection.getOwnerName();
        long userId = collection.getUserId();
        int itemsSize=collection.getItemIds().size();
        List<Long> itemIds=itemsRepository.saveAll(collection.getItemIds());//TODO HERE WE SAME ALL THE ITEMAS AND ONCE DONE WE CONTINUE AND SAVE collections rows/
        for (int i = 0;  i < itemsSize; i++) {
            Object itemId = itemIds.get(i);
            Object[] temp = {collectionId, timestamp, collectionName, userId, ownerName, itemId};
            collectionList.add(temp);
        }
        int[] result= jdbcTemplate.batchUpdate(
                 "insert into collection (collectionid,timestamp,collectionname,userid,ownername,itemids) values(?,?,?,?,?,?)", collectionList);
        return result;
    }


    public int updateCollection(Collection collection)     {

        String collectionId=  collection.getCollectionId();
        String collectionName=collection.getCollectionName();
        long userId =collection.getUserId();        
        List<Item> items=collection.getItemIds();
        String ownerName = collection.getOwnerName();
        List<Long> itemIds=itemsRepository.updateItems(items);
        String sqlUpdateCollection = "update collection set userid=? , ownername=? , collectionname =?, collectionid =? where itemids=?";
        this.jdbcTemplate.batchUpdate(
                sqlUpdateCollection,
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i)
                            throws SQLException {
                        ps.setLong(1, userId);
                        ps.setString(2, ownerName);
                        ps.setString(3, collectionName);
                        ps.setString(4, collectionId);
                        ps.setLong(5, itemIds.get(i));
                    }

                    public int getBatchSize() {
                        return items.size()
                                ;
                    }
                });
        return 0;
    }
//BB8CdkPbe4UEWje3jgT3
    @Override
    public int deleteById(Long itemId) {

        return jdbcTemplate.update(
                "delete from collection where itemids=?",
                itemId);

    }

    @Override
    public int deleteByCollectionIds(List<Long> collectionIds) {
        return 0;
    }


    public int deleteByCollectionItemIds(List<Long> ids) {
        StopWatch timer = new StopWatch();

        timer.start();
       int result= itemsRepository.deleteByItems(ids);
        System.out.println("delete result "+timer.getTotalTimeMillis());
        this.jdbcTemplate.batchUpdate(
                "delete from collection where itemids=?",
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i)
                            throws SQLException {
                        ps.setLong(1,ids.get(i));
                    }

                    public int getBatchSize() {
                        return ids.size()
                                ;
                    }
                });
        timer.stop();
        System.out.println("delete a list of itemids "+timer.getTotalTimeMillis());
        return 0;

    }

    public  List<Collection> findById(Long userId){
        String sqlQuery="select * from collection where userid="+userId;
        List<Collection> collections=jdbcTemplate.query(sqlQuery,new CollectionMapper());
        Set<String> nameSet = new HashSet<>();
        List<String> itemIds=new ArrayList<>();
        List<Collection> collectionDistinctByName = collections.stream()
                .filter(e -> nameSet.add(e.getCollectionId()))
                .collect(Collectors.toList());
        Map<String,List<String>> itemIdsByCollectionName=new HashMap<>();
        String prevCollectionName=null;
        for (Collection element : collections) {
            String currentName=element.getCollectionId();
            if(prevCollectionName==null){
                prevCollectionName=currentName;
            }
            if (prevCollectionName.equals(currentName)){
                itemIds.add(element.getItemId());
            }
            if(!prevCollectionName.equals(currentName)) {
                itemIdsByCollectionName.put(prevCollectionName,new ArrayList<>(itemIds));
                itemIds.clear();
                prevCollectionName = null;
                itemIds.add(element.getItemId());
            }
           else {
                itemIdsByCollectionName.put(prevCollectionName,new ArrayList<>(itemIds));
            }
        }
        for (Map.Entry<String,List<String>> entry : itemIdsByCollectionName.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            itemIds=entry.getValue();
            String collectionName=entry.getKey();
            List<Item> itemList = itemsRepository.findAllItems(itemIds);
            for(int j=0;j<collectionDistinctByName.size();j++) {
                String collectionId = collectionDistinctByName.get(j).getCollectionId();
                if (collectionName.equals(collectionId)) {
                    collectionDistinctByName.get(j).addItems(itemList);
                }
            }
        }

        return collectionDistinctByName;
    }



    private static final class CollectionMapper implements RowMapper<Collection> {

        public Collection mapRow(ResultSet rs, int rowNum) throws SQLException {

            Collection col = new Collection();
            col.setTimestamp(rs.getTimestamp("timestamp"));
            col.setCollectionName(rs.getString("collectionname"));
            col.setUserId(rs.getLong("userid"));
            col.setOwnerName(rs.getString("ownername"));
            col.setItemId(rs.getString("itemids"));
            col.setId(rs.getLong("id"));
            col.setCollectionId(rs.getString("collectionid"));
            return col;
        }

    }


}
