package com.baffintech.bandcraft.controller;

import com.baffintech.bandcraft.database.dao.UserDAO;
import com.baffintech.bandcraft.database.entity.User;
import com.baffintech.bandcraft.form.CreateAccountFormBean;
import com.baffintech.bandcraft.security.AuthenticatedUserUtilities;
import com.baffintech.bandcraft.service.UserService;
import jakarta.servlet.http.HttpSession;
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
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/account")
public class LoginController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;

    @GetMapping("/login")           // just displaying our login page, could just do the 'return a String' approach here
    public ModelAndView doLogin() {
        ModelAndView response = new ModelAndView("auth/login");

        return response;
    }

    @GetMapping("/create-account")
    public ModelAndView createAccount() {
        ModelAndView response = new ModelAndView("auth/create-account");

        return response;
    }

    @PostMapping("/create-account")
    public ModelAndView createAccountSubmit(@Valid CreateAccountFormBean form, BindingResult bindingResult, HttpSession session) {
        ModelAndView response = new ModelAndView("auth/create-account");

        // check to make sure the email does not already exist, but ALSO check to see if its a create
        // using my custom annotation
        // when doing a manual check in the controller, we want this before the binding result.hasErrors check so that it will fall into that block of code
        if(form.getId() == null) {      // if this is a create:
            User u = userDAO.findByUsernameIgnoreCase(form.getUsername());  // find by email, which is the username

            if ( u != null){
                bindingResult.rejectValue("email", "email", "This email is already in use. Manual check.");
            }
        }

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.debug("Validation error : " + ((FieldError) error).getField() + " = " + error.getDefaultMessage());
            }

            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);

        } else {
            userService.createUser(form);
            authenticatedUserUtilities.manualAuthentication(session, form.getUsername(), form.getPassword());
        }
        response.setViewName("redirect:/");
        return response;
    }

}
