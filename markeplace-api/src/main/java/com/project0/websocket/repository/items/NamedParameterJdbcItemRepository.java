package com.project0.websocket.repository.items;

import com.project0.websocket.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class NamedParameterJdbcItemRepository extends ItemsRepository{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int update(Item item) {
        return namedParameterJdbcTemplate.update(
                "update items set price = :price where itemid = :id",
                new BeanPropertySqlParameterSource(item));
    }

    public Optional<Item> findById(Long itemid) {
        try {
            return namedParameterJdbcTemplate.queryForObject(
                    "select * from items where itemid = :itemid",
                    new MapSqlParameterSource("itemid", itemid),
                    (rs, rowNum) ->
                            Optional.of(new Item(
                                    rs.getInt("userid"),
                                    rs.getString("itemid"),
                                    rs.getTimestamp("releasedate"),
                                    rs.getString("tokenuri"),
                                    rs.getBigDecimal("price"),
                                    rs.getBigDecimal("listingprice"),
                                    rs.getString("seller"),
                                    rs.getString("owner"),
                                    rs.getString("image"),
                                    rs.getString("itemname"),
                                    rs.getString("creatorname"),
                                    rs.getString("description"),
                                    rs.getString("hashtag"),
                                    rs.getString("percentage"),
                                    rs.getInt("editionnum")

                            ))
            );
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Item> findByNameAndPrice(String seller, BigDecimal price) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", "%" + seller + "%");
        mapSqlParameterSource.addValue("price", price);

        return namedParameterJdbcTemplate.query(
                "select * from items where seller like :seller and price <= :price",
                mapSqlParameterSource,
                (rs, rowNum) ->
                        new Item(
                                rs.getString("itemid"),
                                rs.getTimestamp("timestamp"),
                                rs.getString("tokenuri"),
                                rs.getBigDecimal("price"),
                                rs.getBigDecimal("listingprice"),
                                rs.getString("seller"),
                                rs.getString("owner"),
                                rs.getString("image"),
                                rs.getString("itemname"),
                                rs.getString("creatorname"),
                                rs.getString("description"),
                                rs.getString("hashtag"),
                                rs.getString("percentage")
                        )
        );
    }

}
