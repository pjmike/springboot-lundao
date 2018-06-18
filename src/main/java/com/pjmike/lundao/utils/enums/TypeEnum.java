package com.pjmike.lundao.utils.enums;

import lombok.Getter;

public enum TypeEnum {
    DEBATE(1),
    THESIS(2),
    COMMENT(3);
    TypeEnum(Integer type) {
        this.type = type;
    }
    @Getter
    private Integer type;

}
