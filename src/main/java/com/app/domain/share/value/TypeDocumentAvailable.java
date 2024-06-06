package com.app.domain.share.value;

import lombok.Getter;

@Getter
public enum TypeDocumentAvailable {

    CC("CC"),
    TI("TI");

    private final String value;

    TypeDocumentAvailable(String name) {
        this.value = name;
    }

}
