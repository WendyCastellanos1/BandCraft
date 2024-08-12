package com.baffintech.bandcraft.controller;

import com.baffintech.bandcraft.config.MyInterceptor;
import com.baffintech.bandcraft.database.dao.MemberDAO;
import com.baffintech.bandcraft.database.entity.Member;
import com.baffintech.bandcraft.database.entity.User;
import com.baffintech.bandcraft.security.AuthenticatedUserUtilities;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import lombok.extern.slf4j.*;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private MyInterceptor myInterceptor;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @GetMapping("/")
    public ModelAndView index() {

        ModelAndView response = new ModelAndView("index");

        //+++++++ Interceptor ++++
        Object handler = new Object();
        myInterceptor.postHandle(httpServletRequest, httpServletResponse, handler, response);

        return response;
    }
}

