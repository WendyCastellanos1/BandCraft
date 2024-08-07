package com.baffintech.bandcraft.controller;

import com.baffintech.bandcraft.database.dao.MemberDAO;
import com.baffintech.bandcraft.database.entity.Member;
import com.baffintech.bandcraft.form.CreateMemberFormBean;
import com.baffintech.bandcraft.security.AuthenticatedUserUtilities;
import com.baffintech.bandcraft.service.MemberService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

    @Slf4j
    @Controller
    @RequestMapping("/member")    // part of URL before the jsp page
    public class MemberController {

        @Autowired
        private MemberDAO memberDAO;
        ;

        @Autowired
        private MemberService memberService;


        // listens on url: localhost:8080/member/list
        @GetMapping("/list")
        public ModelAndView findAll() {

            ModelAndView response = new ModelAndView("member/list");
            List<Member> members = memberDAO.findAll();
            response.addObject("membersKey", members);

            return response;
        }

        // listens on url: localhost:8080/member/id
        @GetMapping("/{id}")
        public ModelAndView detail(@PathVariable Integer id) {

            ModelAndView response = new ModelAndView("member/detail");
            log.debug("The user wants the member with id:  " + id);

            Member member = memberDAO.findById(id);
            response.addObject("memberKey", member);

            return response;
        }

        @GetMapping("/create")
        public ModelAndView create() {

            // this method is setting up the view for rendering
            ModelAndView response = new ModelAndView("member/create");

            return response;
        }

        @PostMapping("/createSubmit")
        public ModelAndView createSubmit(@Valid CreateMemberFormBean form, BindingResult bindingResult) {

            ModelAndView response = new ModelAndView();
            log.debug(form.toString());     // prints the form data to the console using the CreateMemberFormBean form

            // we want to validate the member doesn't exist in the db, but also check if it's a create
            // when doing a manual check in the controller, we want this before the binding result.hasErrors check so that it will fall into that block of code
            // if this is a create:
//            if(form.getId() == null) {
//
//                // TODO - this may be unnecessary Member m = memberDAO.findByUser(form.getName());
//
//                if ( t != null){
//                    bindingResult.rejectValue("name", "name", "This member ? is already in use. Manual check.");
//                }
//            }

            //this is a pattern
            if (bindingResult.hasErrors()) {
                for (ObjectError error : bindingResult.getAllErrors()) {
                    log.debug("Validation error : " + ((FieldError) error).getField() + " = " + error.getDefaultMessage());
                }
                response.addObject("bindingResult", bindingResult); // error has occurred;, use on jsp page to show user the errors

                response.setViewName("member/create");

                response.addObject("form", form);

                return response;

            } else {

                Member member = memberService.createMember(form);

                // this is a URL, NOT a view name
                // in some ways this is overriding the behavior of the setViewName to use a URL rather than a JSP file location
                //response.setViewName("talent/create");

                // redirecting to the talent detail page, but usually you'd go to fully populated EDIT page, take emp id on url and use it to populate all the fields before rendering
                //response.setViewName("redirect:/talent/edit?id=" + form.getId());

                response.setViewName("redirect:/member/detail");

                return response;
            }
        }

        @RequestMapping(value = "/edit", method = {RequestMethod.GET})
        public ModelAndView edit(@RequestParam(required = false) Integer id) {

            // by setting required = false on the incoming parameter we allow
            ModelAndView response = new ModelAndView("member/create");

            // load the employee from the database and set the form bean with all the employee values
            // this is because the form bean is on the JSP page and we need to pre-populate the form with the member data
            if (id != null) {
                // we only do this code if we found the talent in the db
                Member member = memberDAO.findById(id);
                if (member != null) {
                    CreateMemberFormBean form = new CreateMemberFormBean();

                    form.setId(member.getId());
                    form.setFirstName(member.getFirstName());
                    form.setLastName(member.getLastName());
                    form.setNickname(member.getNickname());
                    form.setGender(member.getGender());
                    form.setGeneration(member.getGeneration());
                    form.setGeneration(member.getGeneration());
                    form.setBio(member.getBio());
                    form.setPhoneCell(member.getPhoneCell());
                    form.setPhoneAlt(member.getPhoneAlt());
                    // form.setProfilePhoto(member.getProfilePhoto());      // TODO why does it hate member.getProfilePhoto() ?
                    form.setSocialMediaUrl(member.getSocialMediaUrl());
                    form.setSpeaksPortuguese((member.getSpeaksPortuguese()));
                    form.setSpeaksSpanish(member.getSpeaksSpanish());
                    form.setIsBanned(member.getIsBanned());
                    form.setIsActive(member.getIsActive());
                    form.setDateCreated(member.getDateCreated());
                    form.setDateUpdated(member.getDateUpdated());
                    form.setLastUpdatedId(member.getLastUpdatedId());

                    response.addObject("form", form);
                }
            }
            return response;
        }

        // listens on url: localhost:8080/talent/search
        @GetMapping("/search")
        public ModelAndView search(@RequestParam(required = false) String search) {

            ModelAndView response = new ModelAndView("member/search");
            log.debug("The user searched for the term: " + search);

            // Add the user input back to the model so that we can display the search term in the input field
            response.addObject("searchKey", search);

            List<Member> talents = memberDAO.findByFirstName(search);
            response.addObject("talentsKey", talents);

            return response;
        }
    }

