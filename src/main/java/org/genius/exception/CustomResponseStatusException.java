package org.genius.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Setter
@Getter
public class CustomResponseStatusException extends NestedRuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int status;
    private final String reason;
    private final HttpHeaders headers;

    public CustomResponseStatusException(HttpStatus status, HttpHeaders headers, String reason, Throwable cause) {
        super(reason, cause);
        this.status = status.value();
        this.reason = reason;
        this.headers = headers;
    }
}
