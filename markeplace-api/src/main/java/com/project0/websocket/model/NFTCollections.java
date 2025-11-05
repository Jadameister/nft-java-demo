package com.project0.websocket.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name="collections")
public class NFTCollections {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="Europe/Berlin")
    private Timestamp timestamp;
    @Column(name="collectionsname")
    private String collectionsName;
    @Column(name="userid")
    private int userId;
    @Column(name="collectionid")
    private ArrayList<String> collectionItems;

    public NFTCollections(){

    }

    public NFTCollections(long id, Timestamp timestamp, String collectionsName, int userId, String collectionId){
        this.id=id;
        this.timestamp=timestamp;
        this.collectionsName=collectionsName;
        this.userId=userId;
        this.collectionItems= new ArrayList<>();
        this.collectionItems.add(collectionId);
    }

    public NFTCollections(String collectionsName, int userId, List<String> collectionIds){

        this.collectionsName=collectionsName;
        this.userId=userId;
        this.collectionItems= new ArrayList<>();
        this.collectionItems.addAll(collectionIds);
    }


    public String toString() {
        return String.format("id=%d, collectionsName='%s', userid='%s',collectionids='%s'",
                id, collectionsName, userId,collectionItems.toString());
    }


}
