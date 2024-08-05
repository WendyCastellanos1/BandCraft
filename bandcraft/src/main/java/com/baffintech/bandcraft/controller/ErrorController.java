package com.baffintech.bandcraft.controller;

import com.baffintech.bandcraft.security.AuthenticatedUserUtilities;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.beans.ConstructorProperties;

@Slf4j
@Controller
@ControllerAdvice
public class ErrorController {

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;

    // the @ControllerAdvice annotation is used to define as an exception handler

    // this is optional ... im just showing you some things .. this is a catch all bucket for 404 errors
    // I am using this in seriesreminder because I need to do additional processing for a 404 page
    @ExceptionHandler(NoResourceFoundException.class)
    @RequestMapping(value = {"/error/404", "/404"})
    public ModelAndView error404(HttpServletRequest request) {
        // This is used in the security config for 404 pages
        ModelAndView response = new ModelAndView("error/404");

        log.debug("!!!!!!!!!!!!!!!!!! IN ERROR CONTROLLER : 404 NOT FOUND : " + request.getMethod() + " " + request.getRequestURI());

        // this line of code is specifically setting a 404 status code
        response.setStatus(HttpStatus.NOT_FOUND);

        return response;
    }

    private String getHTMLStackTrace(String[] stack) {
        StringBuffer result = new StringBuffer();
        for (String frame : stack) {
            // Change this to be your package name
            if (frame.contains("com.example.springboot")) {
                result.append(" &nbsp; &nbsp; &nbsp;" + frame.trim().substring(3) + "<br>\n");
            } else if (frame.contains("Caused by:")) {
                result.append("Caused By:<br>");
            }
        }

        return result.toString();
    }

    // this is not an expectation for the case study - this is just extra to understand a little about how error controller works
    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(HttpServletRequest request, Exception ex) {
        log.warn("Error page exception : " + ex.getMessage(), ex);

        ModelAndView response = new ModelAndView("error/500");

        if (authenticatedUserUtilities.isUserInRole("ADMIN")) {
            response.addObject("requestUrl", request.getRequestURI());
            response.addObject("message", ex.getMessage());

            String stackTrace = getHTMLStackTrace(ExceptionUtils.getStackFrames(ex));
            response.addObject("stackTrace", stackTrace);
            if (ex.getCause() != null) {
                response.addObject("rootCause", ExceptionUtils.getRootCause(ex));

                String rootTrace = getHTMLStackTrace(ExceptionUtils.getRootCauseStackTrace(ex));
                response.addObject("rootTrace", rootTrace);
            }
        }

        return response;
    }



}

// I added "throws exception to a controller method
// I made   error directory under jsp directory, put 404.jsp in it


