package com.app.domain.thirdpartylimit.model.value;

public class UUID {

    private final String value;

    public UUID() {
        this.value = java.util.UUID.randomUUID().toString();
    }
    public  String value(){
         return value;
     }
}
