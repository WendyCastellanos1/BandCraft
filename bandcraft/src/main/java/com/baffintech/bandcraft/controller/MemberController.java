package com.baffintech.bandcraft.controller;

import com.baffintech.bandcraft.config.MyInterceptor;
import com.baffintech.bandcraft.database.dao.MemberDAO;
import com.baffintech.bandcraft.database.dao.MemberTalentDAO;
import com.baffintech.bandcraft.database.dao.UserDAO;
import com.baffintech.bandcraft.database.entity.Band;
import com.baffintech.bandcraft.database.entity.Member;
import com.baffintech.bandcraft.database.entity.MemberTalent;
import com.baffintech.bandcraft.database.entity.User;
import com.baffintech.bandcraft.form.CreateBandFormBean;
import com.baffintech.bandcraft.form.CreateMemberFormBean;
import com.baffintech.bandcraft.security.AuthenticatedUserUtilities;
import com.baffintech.bandcraft.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

    @Slf4j
    @Controller
    @RequestMapping("/member")    // part of URL before the jsp page
    public class MemberController {

        @Autowired
        private MemberDAO memberDAO;

        @Autowired
        private MemberService memberService;

        @Autowired
        private MemberTalentDAO memberTalentDAO;

        @Autowired
        private AuthenticatedUserUtilities authenticatedUserUtilities;

        @Autowired
        private UserDAO userDAO;

        @Autowired
        private MyInterceptor myInterceptor;

        @Autowired
        private HttpServletRequest httpServletRequest;

        @Autowired
        private HttpServletResponse httpServletResponse;

        // listens on url: localhost:8080/member/list
        @GetMapping("/list")
        public ModelAndView findAll() {

            ModelAndView response = new ModelAndView("member/list");

            //+++++++ Interceptor ++++
            Object handler = new Object();
            myInterceptor.postHandle(httpServletRequest, httpServletResponse, handler, response);

            List<Member> members = memberDAO.findAll();
            response.addObject("membersKey", members);

            return response;
        }

        // listens on url: localhost:8080/member/id
        @GetMapping("/{id}")
        public ModelAndView detail(@PathVariable Integer id) {

            ModelAndView response = new ModelAndView("member/detail");

            //+++++++ Interceptor ++++
            Object handler = new Object();
            myInterceptor.postHandle(httpServletRequest, httpServletResponse, handler, response);

            log.debug("The user wants the member with id:  " + id);

            Member member = new Member();
            User user = authenticatedUserUtilities.getCurrentUser();

            log.debug("The user wants the user with id:  " + user.getId());

            // get the member
            if (id == null) {
                member = memberDAO.findByUser(user);
            } else {
                member = memberDAO.findById(id);
            }

            response.addObject("memberIdKey", member.getId());
            response.addObject("memberKey", member);

            List<MemberTalent> memberTalents = memberTalentDAO.findByMember(member);
            response.addObject("memberTalentsKey", memberTalents);

            return response;
        }

        @GetMapping("/create")
        public ModelAndView create(@RequestParam(required = false) Integer id) {                                  // this method is setting up the view for rendering

            ModelAndView response = new ModelAndView("member/create");

            //+++++++ Interceptor ++++
            Object handler = new Object();
            myInterceptor.postHandle(httpServletRequest, httpServletResponse, handler, response);

            // build the options for the select dropdown for birth generation (because static, not from table due to table number limitations in requirements)
            String generationOptions = memberService.generationOptionsBuild("u");
            response.addObject("generationOptionsKey", generationOptions);

            return response;
        }

        @PostMapping("/createSubmit")
        public ModelAndView createSubmit(@Valid CreateMemberFormBean form, BindingResult bindingResult) {

            ModelAndView response = new ModelAndView();

            //+++++++ Interceptor ++++
            Object handler = new Object();
            myInterceptor.postHandle(httpServletRequest, httpServletResponse, handler, response);

            log.debug(form.toString());                                                                         // prints the form data to the console using the CreateMemberFormBean form

            // TODO make sure we don't add the same user twice as user or member or in duplicate user roles
            if (bindingResult.hasErrors()) {
                for (ObjectError error : bindingResult.getAllErrors()) {
                    log.debug("Validation error : " + ((FieldError) error).getField() + " = " + error.getDefaultMessage());
                }
                response.addObject("bindingResult", bindingResult);                     // error has occurred;, use on jsp page to show user the errors
                response.setViewName("member/create");
                response.addObject("form", form);

                return response;

            } else {

                Member member = memberService.createMember(form);                                           // saves the member to the db

                response.setViewName("redirect:../member/edit?id=" + member.getId());                          // this is a URL, NOT a view name

                return response;
            }
        }

        @RequestMapping(value = "/edit", method = {RequestMethod.GET})
        public ModelAndView edit(@RequestParam(required = false) Integer id) {

            ModelAndView response = new ModelAndView("member/create");

            //+++++++ testing Interceptor ++++
            Object handler = new Object();
            myInterceptor.postHandle(httpServletRequest, httpServletResponse, handler, response);

            Member member = new Member();
            User user = authenticatedUserUtilities.getCurrentUser();

            // get the member
            if (id == null) {
                member = memberDAO.findByUser(user);
            } else {
                member = memberDAO.findById(id);
            }

            // load the form bean with member data from db
            if (member != null) {
                CreateMemberFormBean form = new CreateMemberFormBean();

                form.setId(member.getId());
                form.setFirstName(member.getFirstName());
                form.setLastName(member.getLastName());
                form.setNickname(member.getNickname());

                String generationOptions = memberService.generationOptionsBuild(member.getGeneration());    // sub in the options list, "selected" has been inserted in the correct option

                form.setGender(member.getGender());
                form.setGenderComment(member.getGenderComment());

                form.setBio(member.getBio());
                form.setPhoneCell(member.getPhoneCell());
                form.setPhoneAlt(member.getPhoneAlt());
                // form.setProfilePhoto(member.getProfilePhoto());                         // TODO show the path to the file that was uploaded, or show photo via link, better
                form.setSocialMediaUrl(member.getSocialMediaUrl());

                if (member.isSpeaksSpanish()) {
                    form.setSpeaksSpanish(true);                                           // when it's true, add the word "checked" in the checkbox code on the jsp page
                }

                if (member.isSpeaksPortuguese()) {
                    form.setSpeaksPortuguese(true);                                          // it's true, so add the word "checked" in the checkbox code on jsp page
                }

                form.setDateCreated(member.getDateCreated());
                form.setDateUpdated(member.getDateUpdated());
                form.setLastUpdatedId(member.getLastUpdatedId());

                response.addObject("form", form);
                response.addObject("generationOptionsKey", generationOptions);

               // response.setViewName("redirect:/member/create?id=" + member.getId());
                // TODO wishful thinking: response.setViewName("member-talent/create?memberId=" + member.getId());
            }
            return response;
        }
    }




//            // listens on url: localhost:8080/talent/search
//            @GetMapping("/search")
//            public ModelAndView search (@RequestParam (required = false) String search) {
//
//                ModelAndView response = new ModelAndView("member/search");
//
//               //+++++++ Interceptor ++++
//                Object handler = new Object();
//                myInterceptor.postHandle(httpServletRequest, httpServletResponse, handler, response);

//                log.debug("The user searched for the term: " + search);
//
//                // Add the user input back to the model so that we can display the search term in the input field
//                response.addObject("searchKey", search);
//
//                List<Member> members = memberDAO.findByFirstName(search);
//                response.addObject("membersKey", members);
//
//                return response;
//            }


