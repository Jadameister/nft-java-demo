package com.project0.websocket.controller;

import com.project0.websocket.model.*;
import com.project0.websocket.repository.collection.CollectionRepository;
import com.project0.websocket.repository.collections.CollectionsRepository;
import com.project0.websocket.repository.items.ItemJPARepository;
import com.project0.websocket.repository.items.ItemsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class RestAPIController {

  @Autowired
  ItemsRepository  itemsRepository;
  @Autowired
  CollectionRepository collectionRepository;
  @Autowired
  CollectionsRepository collectionsRepository;
  @Autowired
  ItemJPARepository itemJPARepository;

  Logger logger = LoggerFactory.getLogger(RestAPIController.class);

  public RestAPIController() {

  }

  @MessageMapping("/sendMessage")
  public void receiveMessage(@Payload ItemDTO textMessageDTO) {
    // receive message from client
    System.out.println(textMessageDTO);
  }
  @MessageMapping("/createItem")
  public void save(Item item) {
    Assertions.assertNotNull(item,"Item should not be null");
   itemsRepository.save(item);
  }
  @CrossOrigin(origins = {"http://localhost:3000"})
  @PostMapping("/addCollection")
  public Map<Object, Object> saveCollection(@RequestBody Collection collection) {
    logger.info( "Process the request save collection.");
    Assertions.assertNotNull(collection,"collection should not be null");
    collectionRepository.save(collection);
    Map<Object, Object> response=new HashMap<>();
    response.put("status","success");
    return response;
  }

  @PutMapping("/updateCollection")
  public ResponseEntity<Void> updateCollection(@RequestBody Collection collection) {
    Assertions.assertNotNull(collection,"collection should not be null");
    collectionRepository.updateCollection(collection);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  @PostMapping("/deleteCollection")
  public ResponseEntity<Void> deleteCollection(@RequestBody Collection collection) {
    Assertions.assertNotNull(collection,"items should not be null");
    //collectionRepository.deleteByCollectionIds(collection.getCollectionId());
    return new ResponseEntity<>(HttpStatus.OK);
  }
  @PostMapping("/deleteItems")
  public ResponseEntity<Void> deleteItems(@RequestBody  ItemsList itemIds) {
    Assertions.assertNotNull(itemIds,"items should not be null");
    collectionRepository.deleteByCollectionItemIds(itemIds.getItemIds());
    return new ResponseEntity<>(HttpStatus.OK);
  }
//TODO USE THIS FOR THE GET CALLS https://github.com/bezkoder/spring-boot-jpa-paging-sorting/tree/master/src/main/java/com/bezkoder/spring/data/jpa/pagingsorting/repository
  @GetMapping(value = "/myCollection/{id}")
  public List<Collection> getCollectionsInfoByUserId(@PathVariable Long id){
    try {
      List<Collection> collectionList = collectionRepository.findById(id);
      System.out.print("collectionList info"+ collectionList.toString());
      return collectionList;
    }
    catch (NullPointerException exc) {
      throw new ResponseStatusException(
              HttpStatus.NOT_FOUND, "user Collection Not Found", exc);
    }

  }
  @SendTo("/topic/message")
  public String broadcastMessage(@Payload Item textMessageDTO)
  {
    ///textMessageDTO.setMessage("THS IS A TEST MESSAGE");
    System.out.println(textMessageDTO.toString());
    System.out.println("********************************");
    return textMessageDTO.toString();
  }
}
