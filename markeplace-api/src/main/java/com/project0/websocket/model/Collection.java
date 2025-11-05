package com.project0.websocket.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name="collectionid")
    private String collectionId;
    @JsonFormat(shape= JsonFormat.
            Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="Europe/Berlin")
    private Timestamp timestamp;
    @Column(name="collectionname")
    private String collectionName;
    @Column(name="userid")
    private long userId;
    @Column(name="ownername")
    private String ownerName;
   // @Column(name="itemids")
    private String itemId;
   @ManyToOne(targetEntity = Item.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name="itemids")
    private List<Item> itemIds=new ArrayList<>();
    public Collection(){

    }

    public Collection(String collectionName,int userId,String ownerName,Set<Item> items){
        this.collectionName=collectionName;
        this.userId=userId;
        this.ownerName=ownerName;
        this.itemIds.addAll(items);
    }

   public Collection(String collectionName,String collectionId,int userId,String ownerName,Set<Item> items){
       this.collectionName=collectionName;
       this.collectionId=collectionId;
       this.userId=userId;
       this.ownerName=ownerName;
       this.itemIds.addAll(items);
   }

    public Collection(Long id,String collectionId,Timestamp timestamp,String collectionName,int userId,String ownerName,Item item){
        this.id=id;
        this.collectionId=collectionId;
        this.timestamp=timestamp;
        this.collectionName=collectionName;
        this.userId=userId;
        this.ownerName=ownerName;
        this.itemIds.add(item);
    }

   public  Collection(String collectionName,String collectionId,int userId,String itemId){
       this.collectionName=collectionName;
       this.collectionId=collectionId;
       this.userId=userId;
       this.itemId=itemId;
   }

    public Collection(long id, String collectionid, Timestamp timestamp, String collectionname, int userid, String ownername, String itemid) {
        this.id=id;
        this.collectionId=collectionid;
        this.timestamp=timestamp;
        this.collectionName=collectionname;
        this.userId=userid;
        this.ownerName=ownername;
        this.itemId= itemid;

    }

    public void addItems(List<Item> items){
        this.itemIds.addAll(items);
    }

    public String toString() {
        return String.format("id=%d, collectionname='%s', userid='%s', ownername='%s',itemids='%s'",
                id, collectionName, userId, ownerName,itemId);
    }

}
