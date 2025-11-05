package com.project0.websocket.model;


public class ItemDTO {
  private Item item;

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    System.out.println("We now going to add an item");

    this.item = item;
  }
}
