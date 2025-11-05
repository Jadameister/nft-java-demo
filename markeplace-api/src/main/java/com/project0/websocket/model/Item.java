package com.project0.websocket.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
@Getter
@Setter
@Entity
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name="itemid")
    private String itemId;
    @Column(name="releasedate")
    @JsonFormat(shape= JsonFormat.
            Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="Europe/Berlin")
    private Timestamp releaseDate;
    @Column(name="tokenuri")
    private String tokenUri;
    @Column(name="price")
    private BigDecimal price;
    @Column(name="listingprice")
    private BigDecimal listingPrice;
    @Column(name="seller")
    private String seller;
    @Column(name="owner")
    private String owner;
    @Column(name="image")
    private String image;
    @Column(name="itemname")
    private String itemName;
    @Column(name="creatorname")
    private String creatorName;
    @Column(name="description")
    private String description;
    @Column(name="userid")
    private int userId;
    @Column(name="hashtag")
    private String itemHashtags;
    @Column(name="percentage")
    private String percentage;
    @Column(name="editionnum")
    private int editionNum;
    public Item(){


    }

    public Item(int userId,String itemId,Timestamp releaseDate,String tokenUri, BigDecimal price, BigDecimal listingPrice,String seller,String owner,String image,String itemName, String creatorName,String description,String itemHashtags,String percentage,int  editionNum) {
        this.releaseDate=releaseDate;
        this.tokenUri = tokenUri;
        this.price = price;
        this.listingPrice = listingPrice;
        this.seller=seller;
        this.owner=owner;
        this.image=image;
        this.itemName=itemName;
        this.description=description;
        this.userId=userId;
        this.itemHashtags=itemHashtags;
        this.percentage=percentage;
        this.editionNum=editionNum;
        this.creatorName=creatorName;
        this.itemId=itemId;
    }

    public Item(int userId,Timestamp releaseDate,String tokenUri, BigDecimal price, BigDecimal listingPrice,String seller,String owner,String image,String itemName, String creatorName,String description,String itemHashtags,String percentage,int  editionNum) {
        this.releaseDate=releaseDate;
        this.tokenUri = tokenUri;
        this.price = price;
        this.listingPrice = listingPrice;
        this.seller=seller;
        this.owner=owner;
        this.image=image;
        this.itemName=itemName;
        this.description=description;
        this.userId=userId;
        this.itemHashtags=itemHashtags;
        this.percentage=percentage;
        this.editionNum=editionNum;
        this.creatorName=creatorName;

    }

    public Item(String itemId,Timestamp releaseDate,String tokenUri, BigDecimal price, BigDecimal listingPrice,String seller,String owner,String image,String itemName,String creatorName, String description,String itemHashtags,String percentage) {
        this.itemId=itemId;
        this.releaseDate=releaseDate;
        this.tokenUri = tokenUri;
        this.price = price;
        this.listingPrice = listingPrice;
        this.seller=seller;
        this.owner=owner;
        this.image=image;
        this.itemName=itemName;
        this.description=description;
        this.itemHashtags=itemHashtags;
        this.percentage=percentage;
        this.creatorName=creatorName;
    }



    public String toString() {
        return String.format("itemId=%s,userId=%d, releaseDate='%s',tokenUri='%s', price='%s', listingPrice='%s',seller='%s',owner='%s',image='%s',itemName='%s',creatorName='%s',description='%s',itemHashtags='%s',percentage='%s'",
                 itemId,userId, releaseDate.toString(),tokenUri, price.toString(), listingPrice.toString(),seller,owner,image,itemName,creatorName,description,itemHashtags,percentage);
    }

    public Object[] toArray(){
        return new Object[] {this.itemId, this.tokenUri, this.price, this.listingPrice, this.seller, this.owner,this.image, this.itemName,this.creatorName, this.description,this.itemHashtags,this.percentage};

    }
}



