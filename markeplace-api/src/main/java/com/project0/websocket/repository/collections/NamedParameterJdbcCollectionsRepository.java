package com.project0.websocket.repository.collections;

import com.project0.websocket.model.Item;
import com.project0.websocket.model.NFTCollections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class NamedParameterJdbcCollectionsRepository extends CollectionsRepository{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public Optional<NFTCollections> findById(Long userId) {
        try {
            return namedParameterJdbcTemplate.queryForObject(
                    "select * from collections where userId = :userId",
                    new MapSqlParameterSource("userId", userId),
                    (rs, rowNum) ->
                            Optional.of(new NFTCollections(
                                    rs.getLong("id"),
                                    rs.getTimestamp("timestamp"),
                                    rs.getString("collectionsname"),
                                    rs.getInt("userid"),
                                    rs.getString("collectionid")

                            ))
            );
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }


    }



}
