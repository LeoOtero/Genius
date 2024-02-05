package org.genius.exception;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Dictionary of errors.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@Getter
public enum AppErrorType {
    // Errors
    OTHER(ErrorProvider.INTERNAL, "Other error."),
    INEXISTENT_COMPANY_ID(ErrorProvider.EXTERNAL, "Company does not exist."),
    AMOUNT_EXCEEDED(ErrorProvider.EXTERNAL, "You have not enough funds!.");

    private ErrorProvider provider;
    private String description;


    @Override
    public String toString() {
        return "Error Type: provider: " + provider + "; description: " + description;
    }

    @AllArgsConstructor
    @Getter
    public enum ErrorProvider {
        INTERNAL(),
        EXTERNAL
    }

}
