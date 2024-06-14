package org.nott.common.exception;

/**
 * @author Nott
 * @date 2024-6-14
 */
public class HttpFetchException extends RuntimeException {
    public HttpFetchException() {
    }

    public HttpFetchException(String message) {
        super(message);
    }

    public HttpFetchException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpFetchException(Throwable cause) {
        super(cause);
    }

    public HttpFetchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
