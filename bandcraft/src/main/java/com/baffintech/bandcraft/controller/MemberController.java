package com.baffintech.bandcraft.controller;

import com.baffintech.bandcraft.database.dao.MemberDAO;
import com.baffintech.bandcraft.database.entity.Band;
import com.baffintech.bandcraft.database.entity.Member;
import com.baffintech.bandcraft.form.CreateBandFormBean;
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

    @Slf4j
    @Controller
    @RequestMapping("/member")    // part of URL before the jsp page
    public class MemberController {

        @Autowired
        private MemberDAO memberDAO;

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
        public ModelAndView create(@RequestParam(required=false) Integer id) {                                  // this method is setting up the view for rendering

            ModelAndView response = new ModelAndView("member/create");
            return response;
        }

        @PostMapping("/createSubmit")
        public ModelAndView createSubmit(@Valid CreateMemberFormBean form, BindingResult bindingResult) {

            ModelAndView response = new ModelAndView();
            log.debug(form.toString());                                                                         // prints the form data to the console using the CreateMemberFormBean form
            // note: to become a user, they had a unique email. once authenticated, they can only "become a member" *once* with that email because the role is set upon membership, so role controls menu...no additional attempts to create duplicate possible from member through GUI

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

                String generationOptions = memberService.generationOptionsBuild(member.getGeneration());    // sub in the options list, "selected" has been inserted in the correct option
                response.addObject("generationOptionsKey", generationOptions);

                response.setViewName("redirect:/member/edit?id=" + member.getId());                          // this is a URL, NOT a view name

                return response;
            }
        }

        @RequestMapping(value = "/edit", method = {RequestMethod.GET})
        public ModelAndView edit(@RequestParam (required = false) Integer id) {

            ModelAndView response = new ModelAndView("member/create");

            // load the member from the database and set the form bean with all the member values bc form bean is on the JSP page, need to pre-populate the form with the member data
            if (id != null) {
                Member member = memberDAO.findById(id);
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
                        form.setSpeaksSpanish(true);                                           // it's true, so add the word "checked" in the checkbox code on the jsp page
                    }

                    if (member.isSpeaksPortuguese()){
                        form.setSpeaksPortuguese(true);                                          // it's true, so add the word "checked" in the checkbox code on jsp page
                    }

                    form.setDateCreated(member.getDateCreated());
                    form.setDateUpdated(member.getDateUpdated());
                    form.setLastUpdatedId(member.getLastUpdatedId());

                    response.addObject("form", form);
                    response.addObject("generationOptionsKey", generationOptions);
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

            List<Member> members = memberDAO.findByFirstName(search);
            response.addObject("membersKey", members);

            return response;
        }
    }

