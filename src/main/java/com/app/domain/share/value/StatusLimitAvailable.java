package com.app.domain.share.value;

import lombok.Getter;

@Getter
public enum StatusLimitAvailable {

    ENABLE("ACTIVO"),
    DELETED("ELIMINADO");

    private final String value;

    StatusLimitAvailable(String value) {
        this.value = value;
    }


}
