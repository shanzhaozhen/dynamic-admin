package org.shanzhaozhen.dynamicadmin.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultException extends RuntimeException {

    private int code;

    public ResultException() {
    }


    public ResultException(int code, String message) {
        super(message);
        this.code = code;
    }

}
