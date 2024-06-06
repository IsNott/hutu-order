package org.nott.common.advice;

import cn.dev33.satoken.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.nott.common.ResponseEntity;
import org.nott.common.configuration.HutuBizException;
import org.nott.common.exception.PasswordNotMatchesException;
import org.nott.common.exception.UserNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;
import java.util.List;

/**
 * @author Nott
 * @date 2024-5-24
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdviceController {

    @ExceptionHandler(HutuBizException.class)
    public ResponseEntity<Void> handleHutuBizException(HutuBizException hutuBizException) {
        log.error("捕获到自定义业务异常:{}", hutuBizException.getMessage(), hutuBizException);
        return ResponseEntity.failure("系统异常");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("捕获到方法参数检测异常：{}", e.getMessage());
        BindingResult result = e.getBindingResult();
        List<ObjectError> allErrors = result.getAllErrors();
        String defaultMessage = allErrors.get(0).getDefaultMessage();
        return ResponseEntity.failure(defaultMessage);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Void> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.failure("用户名不存在", 402);
    }

    @ExceptionHandler(PasswordNotMatchesException.class)
    public ResponseEntity<Void> handleMethodArgumentNotValidException(PasswordNotMatchesException e) {
        return ResponseEntity.failure("密码不正确", 402);
    }

    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<Void> handleMethodArgumentNotValidException(NotLoginException e) {
        return ResponseEntity.failure("还未登录", 401);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> handleRuntimeException(RuntimeException e) {
        log.error("捕获到运行时异常：{}", e.getMessage(), e);
        return ResponseEntity.failure("系统错误", 500);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Void> handleRuntimeException(ConnectException e) {
        log.error("捕获到连接异常：{}", e.getMessage(), e);
        return ResponseEntity.failure("系统错误", 500);
    }

}
