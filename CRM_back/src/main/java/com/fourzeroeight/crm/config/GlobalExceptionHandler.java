package com.fourzeroeight.crm.config;

import com.fourzeroeight.crm.bean.Message;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Message jsonExceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return new Message(0,e.getMessage(),null);
    }
}