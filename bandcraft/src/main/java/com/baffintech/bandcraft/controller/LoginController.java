package com.baffintech.bandcraft.controller;

import com.baffintech.bandcraft.database.dao.UserDAO;
import com.baffintech.bandcraft.form.CreateAccountFormBean;
import com.baffintech.bandcraft.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/account")
public class LoginController {

        @Autowired
        UserDAO userDAO;

        @Autowired
        UserService userService;

        @GetMapping("/create-account")
        public ModelAndView createAccount() {
            ModelAndView response = new ModelAndView("auth/create-account");

            return response;
        }

        @PostMapping("/create-account")
        public ModelAndView createAccountSubmit(@Valid CreateAccountFormBean form, BindingResult bindingResult) {
            ModelAndView response = new ModelAndView("auth/create-account");

            // hw: check to make sure the email does not already exist
            // this is a great case for the custom annotation that we made

            if (bindingResult.hasErrors()) {
                for (ObjectError error : bindingResult.getAllErrors()) {
                    log.debug("Validation error : " + ((FieldError) error).getField() + " = " + error.getDefaultMessage());
                }

                response.addObject("bindingResult", bindingResult);
                response.addObject("form", form);
            } else {

                userService.createUser(form);
            }
            return response;
        }

        @GetMapping("/login")
        public ModelAndView doLogin() {
            ModelAndView response = new ModelAndView("auth/login");

            return response;
        }
    }
