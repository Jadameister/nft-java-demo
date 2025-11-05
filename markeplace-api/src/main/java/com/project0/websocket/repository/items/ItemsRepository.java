package com.project0.websocket.repository.items;

import com.project0.websocket.model.Item;
import com.project0.websocket.rands.RandomUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;



//https://mkyong.com/spring-boot/spring-boot-jdbc-examples/
@Repository
public class ItemsRepository implements ItemRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    private List itemIds;


    public int count() {
        try {
            return jdbcTemplate.queryForObject("select count(*) from items", Integer.class);
        }
        catch (NullPointerException e)
        {
            return 0;
        }
    }

    private int getLastItemIdValue(){

        if(count()==0){
            return 0;
        }
        else {
            try {

                return jdbcTemplate.queryForObject("SELECT max(itemid) FROM items", Integer.class);
            } catch (NullPointerException e) {
                System.out.println("db query returned a null value itemid as its query into database.");
                return 0;
            }
        }
    }
    public int save(Item item) {
        //long itemId=getLastItemIdValue();
        String itemId= RandomUUID.generateRandomItemId();
        //itemId++;
        item.setItemId(itemId);
        return jdbcTemplate.update(
                "insert into items (itemid, userid,editionnum,tokenuri,price,listingprice,seller,owner,image,itemname,creatername,description,hashtag,percentage,releasedate) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
                itemId,item.getUserId() ,item.getReleaseDate().toString(),item.getTokenUri(),item.getPrice(),item.getListingPrice(),item.getSeller(),item.getOwner(),item.getImage(),item.getItemName(),item.getDescription(),item.getItemHashtags(),item.getPercentage());

    }
    public List<Long> saveAll(List<Item> items) {
        this.itemIds=new ArrayList();
        String itemId=RandomUUID.generateRandomItemId();
        List<Object[]> collectionList = new ArrayList<Object[]>();
        for (Item tempItem : items) {
            Object[] temp = {itemId, tempItem.getUserId(), tempItem.getEditionNum(), tempItem.getTokenUri(), tempItem.getPrice(), tempItem.getListingPrice(), tempItem.getSeller(),
                    tempItem.getOwner(), tempItem.getImage(), tempItem.getItemName(), tempItem.getCreatorName(), tempItem.getDescription(), tempItem.getItemHashtags(), tempItem.getPercentage(), tempItem.getReleaseDate().toString()};
            itemIds.add(itemId);
            itemId = RandomUUID.generateRandomItemId();
            collectionList.add(temp);
        }
        int [] result=jdbcTemplate.batchUpdate(
                "insert into items (itemid, userid, editionnum, tokenuri,price, listingprice, seller, owner, image, itemname, creatername, description, hashtag, percentage, releasedate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", collectionList);

        return itemIds;

    }

    public int updateItemPrice(Item item) {
        return jdbcTemplate.update(
                "update items set price = ? and tokenUri = ? and owner=?   where itemid = ?",
                item.getPrice(),item.getTokenUri(), item.getOwner(), item.getItemId());

    }

    public int updateItem(Item item) {
        return jdbcTemplate.update(
                "update items set price = ? and tokenUri = ? and owner=? and image=? and itemname=? and description=? and hashtag=?  where itemid = ?",
                item.getPrice(),item.getTokenUri(),item.getOwner(),item.getImage(),item.getItemName(),item.getDescription(),item.getItemHashtags(), item.getItemId());

    }

    @Override
    public List<Long> updateItems(List<Item> items) {
        StopWatch timer = new StopWatch();
        timer.start();
         this.jdbcTemplate.batchUpdate(
                "update items set price = ?, owner = ?, itemname=? , description=? , hashtag=?, percentage=?  where itemid = ?",
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i)
                            throws SQLException {
                        ps.setBigDecimal(1, items.get(i).getPrice());
                        ps.setString(2, items.get(i).getOwner());
                        ps.setString(3, items.get(i).getItemName());
                        ps.setString(4, items.get(i).getDescription());
                        ps.setString(5, items.get(i).getItemHashtags());
                        ps.setString(6, items.get(i).getItemId());
                        ps.setString(7, items.get(i).getPercentage());
                    }

                    public int getBatchSize() {
                        return items.size()
                                ;
                    }
                });
        timer.stop();
        System.out.println(timer.getTotalTimeMillis());
         List <Long>ids=new ArrayList<>() ;
        for (Item element : items) {
            ids.add(element.getId());
        }
        return ids;
    }


    public int deleteById(Long itemId) {
        return jdbcTemplate.update(
                "delete item where itemid = ?",
                itemId);
    }

    @Override
    public int deleteByItemsId(List<Long> itemIds) {
        return 0;
    }

    public int deleteByItems(List<Long> itemIds) {
        StopWatch timer = new StopWatch();
        timer.start();
        this.jdbcTemplate.batchUpdate(
                "delete from items where itemid=?",
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i)
                            throws SQLException {
                        ps.setLong(1,itemIds.get(i));
                    }

                    public int getBatchSize() {
                        return itemIds.size()
                                ;
                    }
                });
        timer.stop();
        System.out.println("delete a list of itemids "+timer.getTotalTimeMillis());
        return 0;

    }

    public List<Item> findAll() {
        return jdbcTemplate.query(
                "select * from items",new ItemMapper());
    }

    @Override
    public List<Item> findByNameAndPrice(String name, BigDecimal price) {
        return null;
    }


    public Optional<Item> findById(Long id) {

        return Optional.empty();
    }

    @Override
    public String getNameById(Long id) {
        return null;
    }

    @Override
    public List<String> getItemIds() {
        return null;
    }

    public List<Item> findAllItems(List<String> itemIds){
        int i=0;
        int size=itemIds.size();
        StringBuilder sb=new StringBuilder();
        for (String item : itemIds){
            sb.append('\'');
            sb.append(item);
            i++;
            if (i <size) {
                sb.append('\'');
                sb.append(",");
            }

        }
        sb.append('\'');
        String itemIdArrays="("+ sb.toString()+ ")";
        String sql= String.format("select * from items where itemid in %s ",itemIdArrays);
        
        return jdbcTemplate.query(sql,new ItemMapper());

    }    
    private static final class ItemMapper implements RowMapper<Item> {

        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {

                Item emp = new Item();
                emp.setId(rs.getLong("id"));
                emp.setItemId(rs.getString("itemid"));
                emp.setReleaseDate(rs.getTimestamp("releasedate"));
                emp.setTokenUri(rs.getString("tokenuri"));
                emp.setPrice(rs.getBigDecimal("price"));
                emp.setListingPrice(rs.getBigDecimal("listingprice"));
                emp.setSeller(rs.getString("seller"));
                emp.setOwner(rs.getString("owner"));
                emp.setImage(rs.getString("image"));
                emp.setItemName(rs.getString("itemname"));
                emp.setCreatorName(rs.getString("creatername"));
                emp.setDescription(rs.getString("description"));
                emp.setUserId(rs.getInt("userid"));
                emp.setItemHashtags(rs.getString("hashtag"));
                emp.setPercentage(rs.getString("percentage"));
                return emp;
            }

        }
   

}
