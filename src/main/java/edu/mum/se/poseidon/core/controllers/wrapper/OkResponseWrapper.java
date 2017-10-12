package edu.mum.se.poseidon.core.controllers.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by User on 8/15/2016.
 *
 * @author Yuriy Yugay
 */
public class OkResponseWrapper<T>
        extends AbstractResponseWrapper {

    @JsonProperty("data")
    private T data;

    public OkResponseWrapper(T data) {
        super(Status.success);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
