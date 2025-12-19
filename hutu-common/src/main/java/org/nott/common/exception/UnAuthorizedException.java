package org.nott.common.exception;

/**
 * @author Nott
 * @date 2025-12-01
 */

public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException(String message) {
        super(message);
    }
}
