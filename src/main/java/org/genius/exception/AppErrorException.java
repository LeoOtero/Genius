package org.genius.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class AppErrorException extends NestedRuntimeException {

    private static final long serialVersionUID = 12927101L;

    private final AppError appError;
    private final int status;
    private final HttpHeaders headers;

    public AppErrorException(AppError appError, Throwable cause, HttpStatus status, HttpHeaders headers) {
        super(appError.getMessage(), cause);
        this.appError = appError;
        this.status = status.value();
        this.headers = headers;
    }

}
