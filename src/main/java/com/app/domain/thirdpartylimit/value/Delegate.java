package com.app.domain.thirdpartylimit.value;


public record Delegate(TypeDocument type, String number) {

    public Delegate(TypeDocument type, String number) {
        this.type = type;
        this.number = number;
    }

    public static Delegate of(String type, String number) {

        return new Delegate(new TypeDocument(type), number);
    }

}
