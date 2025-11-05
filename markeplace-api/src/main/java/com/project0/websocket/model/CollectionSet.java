package com.project0.websocket.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CollectionSet {

    private Set<Collection> collectionItems;
    public CollectionSet(){

        this.collectionItems=new HashSet<>();
    }

    public void add(Collection collection){

        this.collectionItems.add(collection);
    }

}
