package com.project0.websocket.model;


import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class ItemIds {

    List<Long> itemIds=new ArrayList<>();

    public ItemIds(List<Long> itemIds){
        this.itemIds.addAll(itemIds);
    }

}
