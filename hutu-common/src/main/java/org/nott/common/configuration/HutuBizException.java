package org.nott.common.configuration;

/**
 * @author Nott
 * @date 2024-5-24
 */
public class HutuBizException extends RuntimeException{

    public HutuBizException() {
    }

    public HutuBizException(String message) {
        super(message);
    }

    public HutuBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public HutuBizException(Throwable cause) {
        super(cause);
    }

    public HutuBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
