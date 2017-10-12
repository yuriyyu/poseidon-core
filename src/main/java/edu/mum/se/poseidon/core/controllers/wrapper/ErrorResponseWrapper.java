package edu.mum.se.poseidon.core.controllers.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.mum.se.poseidon.core.controllers.ErrorCode;

/**
 * Created by User on 8/15/2016.
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ErrorResponseWrapper
        extends AbstractResponseWrapper {

    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private ErrorCode code;

    public ErrorResponseWrapper (String errorMessage) {
        super(Status.error);
        this.message = errorMessage;
    }

    public ErrorResponseWrapper (String errorMessage, ErrorCode errorCode) {
        super(Status.error);
        this.message = errorMessage;
        this.code = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode getCode() {
        return code;
    }
}
