package org.genius.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
@Getter
public class AppError {

    @JsonProperty("type")
    private AppErrorType type;
    private String code;
    private String message;
    private String description;

    public AppError(AppErrorType errorType, String message, String description) {
        this.type = errorType;
        this.code = "0";
        this.message = message;
        this.description = description;
    }

}
