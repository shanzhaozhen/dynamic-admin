package org.shanzhaozhen.dynamicadmin.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultErrorException extends RuntimeException {

    private int code;

    public ResultErrorException() {
    }


    public ResultErrorException(int code, String message) {
        super(message);
        this.code = code;
    }

}
