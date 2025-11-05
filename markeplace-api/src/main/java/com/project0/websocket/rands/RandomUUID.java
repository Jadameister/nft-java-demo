package com.project0.websocket.rands;

import java.util.Locale;
import java.util.UUID;

public class RandomUUID {


    public static String generateRandomItemId(){
        String uuid= UUID.randomUUID().toString();
        StringBuilder sb=new StringBuilder();
        int size=uuid.length();
        uuid=uuid.substring(4,size-6);
        sb.append("IT");
        sb.append(uuid);
        size=sb.toString().length();
        System.out.println(size);
        return sb.toString().toUpperCase(Locale.ROOT);
    }
}
