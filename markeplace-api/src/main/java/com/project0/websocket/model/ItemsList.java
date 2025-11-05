package com.project0.websocket.model;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class ItemsList {

    private List<Long> itemIds = new ArrayList<>();


    public ItemsList() {

    }

    public ItemsList(List<Long> itemIds) {

        this.itemIds.addAll(itemIds);
    }
}
