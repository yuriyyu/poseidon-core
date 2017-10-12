package edu.mum.se.poseidon.core.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by Yuriy Yugay on 1/31/2017.
 *
 * @author Yuriy Yugay
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    COMMON(0),
    USER_EXISTS(1),
    WRONG_CREDENTIALS(2),
    DATA_NOT_FOUND(3);

    private final Integer value;

    ErrorCode(Integer value) {
        this.value = value;
    }

    @JsonCreator
    public static ErrorCode fromValue(Integer key) {
        if(key != null) {
            for(ErrorCode type : values()) {
                if(type.value.equals(key))
                    return type;
            }
        }
        return null;
    }

    @JsonValue
    public Integer toValue() {
        return value;
    }
}
