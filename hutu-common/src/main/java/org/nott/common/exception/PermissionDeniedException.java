package org.nott.common.exception;

/**
 * @author Nott
 * @date 2025-12-01
 */
public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException(String message) {
        super(message);
    }
}
