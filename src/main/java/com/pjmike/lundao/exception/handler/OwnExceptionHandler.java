package com.pjmike.lundao.exception.handler;

import com.aliyuncs.exceptions.ClientException;
import com.pjmike.lundao.exception.ObjectException;
import com.pjmike.lundao.exception.ServiceException;
import com.pjmike.lundao.utils.Result;
import com.pjmike.lundao.utils.ResultCode;
import com.pjmike.lundao.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pjmike
 * @create 2018-05-17 18:05
 */
@RestControllerAdvice
public class OwnExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(OwnExceptionHandler.class);

    @ExceptionHandler(
            {
                    NoHandlerFoundException.class,
                    ServletException.class,
                    ServiceException.class
            }
    )
    public Result exceptionHandler(Exception e, HttpServletRequest request) {
        Result result = new Result();
        if (e instanceof ServiceException) {
            result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
            logger.info("exception:{}", e.getMessage());
        } else if (e instanceof NoHandlerFoundException) {
            result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [ " + request.getRequestURI() + " ] 不存在");
        } else if (e instanceof ServletException) {
            result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
        } else {
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
            logger.error("======" + e.getMessage() + "======");
        }
        return result;
    }

    /**
     * 对参数校验的处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handlerValidaException(MethodArgumentNotValidException ex) {
        logger.info("validation error :{}", getErrors(ex.getBindingResult()));
        return ResultUtils.error(getErrors(ex.getBindingResult()));
    }

    /**
     * 返回校验字段错误的提示信息
     *
     * @param result
     * @return
     */
    private Map<String, String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<>(16);
        //得到所有的属性错误
        List<FieldError> list = result.getFieldErrors();
        //将其组成键值对的形式存入map
        for (FieldError fieldError : list) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return map;
    }

    @ExceptionHandler(ClientException.class)
    public Result SmsExceptionHandler(ClientException x) {
        logger.info("sms send error : {}",x.getMessage());
        return ResultUtils.error("短信通道异常");
    }

    @ExceptionHandler(ObjectException.class)
    public Result objectExceptionHandler(ObjectException x) {
        logger.info("object exception : {}", x.getMessage());
        return ResultUtils.error(x.getMessage());
    }
}
