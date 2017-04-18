package com.github.hesimin.springboot.config;

import com.github.hesimin.springboot.common.Result;
import com.github.hesimin.springboot.common.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author hesimin 2017-04-18
 */
@ControllerAdvice
@ResponseBody//返回json
public class GlobalExceptionHandler {
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public Object MethodArgumentNotValidHandler(HttpServletRequest request,
//                                                MethodArgumentNotValidException exception) throws Exception {
//        //按需重新封装需要返回的错误信息
//        List<Map<String, Object>> invalidArguments = new ArrayList<>();
//        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
//        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
//            Map<String, Object> feildError = new HashMap<>();
//            feildError.put("message", error.getDefaultMessage());
//            feildError.put("field", error.getField());
//            feildError.put("value", error.getRejectedValue());
//            invalidArguments.add(feildError);
//        }
//
//        return new Result().setSuccess(false).setCode("PARAM_ERROR").setMessage("参数错误").setData(invalidArguments);
//    }

    @ExceptionHandler(value = ServiceException.class)
    public Object ServiceExceptionHandler(HttpServletRequest request,
                                          ServiceException ex) throws Exception {
        return new Result().setSuccess(false).setCode(ex.getCode()).setMessage(ex.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public Object RuntimeExceptionHandler(HttpServletRequest request,
                                          RuntimeException ex) throws Exception {
        return new Result().setSuccess(false).setCode("UNKNOWN_ERROR").setMessage("未知错误").setData(ex.getMessage());
    }
}
