package com.app.domain.thirdpartylimit.value;


public record Customer(TypeDocument type, String number) {



    public Customer(TypeDocument type, String number) {
        this.type = type;
        this.number = number;
    }

    public static Customer of(String type, String number) {

        return new Customer(new TypeDocument(type), number);
    }
}
