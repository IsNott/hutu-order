package org.nott.common;

import lombok.Data;

/**
 * @author Nott
 * @date 2024-5-24
 */

@Data
public class ResponseEntity<T> {

    private String message;

    private Integer code;

    private T data;

    public ResponseEntity() {
    }

    public ResponseEntity(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ResponseEntity(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public static <T> ResponseEntity<T> successData(T data) {
        return new ResponseEntity<T>("成功", 200, data);
    }

    public static <T> ResponseEntity<T> successData(String message, T data) {
        return new ResponseEntity<T>(message, 200, data);
    }

    public static <Void> ResponseEntity<Void> success(String message) {
        return new ResponseEntity<Void>(message, 200);
    }

    public static <Void> ResponseEntity<Void> success() {
        return new ResponseEntity<Void>("成功", 200);
    }

    public static <Void> ResponseEntity<Void> failure(String message, Integer code) {
        return new ResponseEntity<Void>(message, code);
    }

    public static <Void> ResponseEntity<Void> failure(String message) {
        return new ResponseEntity<Void>(message, -1);
    }

    public static <Void> ResponseEntity<Void> failure() {
        return new ResponseEntity<Void>("失败", -1);
    }
}
