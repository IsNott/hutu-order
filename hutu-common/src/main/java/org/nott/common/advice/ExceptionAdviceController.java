package org.nott.common.advice;

//import cn.dev33.satoken.exception.NotLoginException;
//import cn.dev33.satoken.exception.NotPermissionException;
import lombok.extern.slf4j.Slf4j;
import org.nott.common.ResponseEntity;
import org.nott.common.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * @author Nott
 * @date 2024-5-24
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdviceController extends ResponseEntityExceptionHandler {

    @Override
    protected org.springframework.http.ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        log.error(message,ex);
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, 0);
        }
        if(ex instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            BindingResult bindingResult = exception.getBindingResult();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            message = allErrors.get(0).getDefaultMessage();
        }
        return new org.springframework.http.ResponseEntity<>(ResponseEntity.failure(message, status.value()), headers, status);
    }

    @ExceptionHandler(HutuBizException.class)
    public ResponseEntity<Void> handleHutuBizException(HutuBizException hutuBizException) {
        log.error("捕获到自定义业务异常:{}", hutuBizException.getMessage(), hutuBizException);
        return ResponseEntity.failure("系统异常");
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        log.error("捕获到方法参数检测异常：{}", e.getMessage());
//        BindingResult result = e.getBindingResult();
//        List<ObjectError> allErrors = result.getAllErrors();
//        String defaultMessage = allErrors.get(0).getDefaultMessage();
//        return ResponseEntity.failure(defaultMessage);
//    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Void> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.failure("用户名不存在", 402);
    }

    @ExceptionHandler(PasswordNotMatchesException.class)
    public ResponseEntity<Void> handlePasswordNotMatchesException(PasswordNotMatchesException e) {
        return ResponseEntity.failure("密码不正确", 402);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<Void> handleNotLoginException(UnAuthorizedException e) {
        return ResponseEntity.failure("还未登录", 401);
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<Void> handleNotPermissionException(PermissionDeniedException e) {
        return ResponseEntity.failure("暂无权限", 403);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> handleRuntimeException(RuntimeException e) {
        log.error("捕获到运行时异常：{}", e.getMessage(), e);
        return ResponseEntity.failure("系统错误", 500);
    }


}
