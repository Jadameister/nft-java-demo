package com.project0.websocket.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Response {

    private String message;
    private boolean status;
    private String error;
    private String data;


    public Response(String message,boolean status,String data){

        this.data=data;
        this.status=status;
        this.message=message;

    }
    public Response(boolean status,String error){
        this.status=status;
        this.error=error;
    }

}
