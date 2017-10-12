package edu.mum.se.poseidon.core.controllers.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by User on 8/15/2016.
 *
 * @author Yuriy Yugay
 */
public class FailResponseWrapper<T>
        extends AbstractResponseWrapper {

    @JsonProperty("data")
    private T data;

    public FailResponseWrapper (T jsonObject) {
        super(Status.fail);
        this.data = jsonObject;
    }

    public Object getData() {
        return data;
    }
}
