package com.baffintech.bandcraft.controller;

import com.baffintech.bandcraft.config.MyInterceptor;
import com.baffintech.bandcraft.database.dao.MemberDAO;
import com.baffintech.bandcraft.database.entity.Member;
import com.baffintech.bandcraft.database.entity.User;
import com.baffintech.bandcraft.database.entity.UserRole;
import com.baffintech.bandcraft.security.AuthenticatedUserUtilities;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import lombok.extern.slf4j.*;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.List;
import java.util.Set;

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


//
//// build a custom message if a *member* lands on this page
//Member member;
//        if (authenticatedUserUtilities.isAuthenticated()) {
//User user = authenticatedUserUtilities.getCurrentUser();
//
//            if (user != null) {
//// get the user's roles
//Set<UserRole> userRoles = user.getUserRoles();
//
//// check if user is in MEMBER role; if MEMBER, build message
//                for (UserRole userRole: userRoles) {
//
//        if (userRole.getRoleName().equals("MEMBER")) {
//
//// get the member
//member = memberDAO.findByUser(user);
//
//// build the message
//String message = "Welcome, " + member.getFirstName() + ". Please login again to re-enter with member privileges.";
//
//// add the message to the response so the jsp page will display it where the token is
//                        response.addObject("messageKey", message);
//                    }
//                            }
//                            }
//                            }
