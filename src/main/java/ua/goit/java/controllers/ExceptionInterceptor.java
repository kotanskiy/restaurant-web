package ua.goit.java.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(Exception.class)
    public String error(HttpServletRequest request, Exception e)   {
        return "error";
    }
}
