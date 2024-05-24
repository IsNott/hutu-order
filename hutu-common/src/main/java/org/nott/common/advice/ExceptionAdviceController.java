package org.nott.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.nott.common.ResponseEntity;
import org.nott.common.configuration.HutuBizException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author Nott
 * @date 2024-5-24
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdviceController {

    @ExceptionHandler(HutuBizException.class)
    public ResponseEntity<Void> handleHutuBizException(HutuBizException hutuBizException){
        log.error("捕获到自定义业务异常:{}",hutuBizException.getMessage(),hutuBizException);
        return ResponseEntity.failure("系统异常");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("捕获到方法参数检测异常：{}",e.getMessage());
        BindingResult result = e.getBindingResult();
        List<ObjectError> allErrors = result.getAllErrors();
        String defaultMessage = allErrors.get(0).getDefaultMessage();
        return ResponseEntity.failure(defaultMessage);
    }
}
