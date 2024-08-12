package com.baffintech.bandcraft.config;

import com.baffintech.bandcraft.database.dao.MemberDAO;
import com.baffintech.bandcraft.database.entity.Member;
import com.baffintech.bandcraft.database.entity.User;
import com.baffintech.bandcraft.security.AuthenticatedUserUtilities;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    AuthenticatedUserUtilities authenticatedUserUtilities;

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {

        Member member = new Member();
        User user = authenticatedUserUtilities.getCurrentUser();

        // if there's a user, get the member
        if (user != null) {
            member = memberDAO.findByUser(user);
        }

        // if there's a member, set the token
        if (member != null) {
            //modelAndView.addObject("memberKey", member);
            modelAndView.addObject("memberIdKey", member.getId());
        }
    }

    //@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor());
    }
}
