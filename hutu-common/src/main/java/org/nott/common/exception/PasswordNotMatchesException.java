package org.nott.common.exception;

/**
 * @author Nott
 * @date 2024-6-6
 */
public class PasswordNotMatchesException extends RuntimeException{

    public PasswordNotMatchesException() {
    }

    public PasswordNotMatchesException(String message) {
        super(message);
    }
}
