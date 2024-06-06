package org.nott.common.exception;

/**
 * @author Nott
 * @date 2024-6-6
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
