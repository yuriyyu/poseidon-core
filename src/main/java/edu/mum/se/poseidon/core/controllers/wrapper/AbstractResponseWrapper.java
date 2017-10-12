package edu.mum.se.poseidon.core.controllers.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by User on 8/15/2016.
 *
 * @author Yuriy Yugay
 */
public abstract class AbstractResponseWrapper {
    @JsonProperty("status")
    private Status status;

    public AbstractResponseWrapper(Status status) {
        this.status = status;
    }

    public enum Status {
        success,
        fail,
        error
    }
}
