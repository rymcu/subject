package com.rymcu.subject.result;

import lombok.Getter;

@Getter
public enum GlobalResultMessage {

    SUCCESS("操作成功！"),
    FAIL("操作失败！"),

    ;

    private String message;

    GlobalResultMessage(String message){
        this.message = message;
    }
}
