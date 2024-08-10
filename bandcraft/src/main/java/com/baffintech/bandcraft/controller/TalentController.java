package com.baffintech.bandcraft.controller;

import com.baffintech.bandcraft.database.dao.MemberTalentDAO;
import com.baffintech.bandcraft.database.dao.TalentDAO;
import com.baffintech.bandcraft.database.entity.Talent;
import com.baffintech.bandcraft.form.CreateTalentFormBean;
import com.baffintech.bandcraft.service.TalentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/talent")    // part of URL before the jsp page
public class TalentController {

    @Autowired
    private TalentDAO talentDAO;;

    @Autowired
    private TalentService talentService;

    @Autowired
    private MemberTalentDAO memberTalentDAO;

    // listens on url: localhost:8080/talent/list
    @GetMapping("/list")
    public ModelAndView findAll() {

        ModelAndView response = new ModelAndView("talent/list");
        List<Talent> talents = talentDAO.findAll();


        //this is a stream with a lambda function
        log.debug("*****************stream********************");
        talents.stream().forEach(talent -> {
           log.debug("Talent: " + talent.getName());
        });

        // is same as above
        log.debug("*****************for loop********************");
        for(Talent talent : talents) {
            log.debug("Talent: " + talent.getName());
        }
        response.addObject("talentsKey", talents);

        return response;
    }

    // listens on url: localhost:8080/talent/id
    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable Integer id) {

        ModelAndView response = new ModelAndView("talent/detail");
        log.debug("The user wants the talent with id:  " +  id);

        Talent talent = talentDAO.findById(id);
        response.addObject("talentKey", talent);

        return response;
    }

    @GetMapping("/create")
    public ModelAndView create() {

        // this method is setting up the view for rendering
        ModelAndView response = new ModelAndView("talent/create");

        return response;
    }

    @PostMapping("/createSubmit")
    public  ModelAndView createSubmit(@Valid CreateTalentFormBean form, BindingResult bindingResult) {

        ModelAndView response = new ModelAndView();
        log.debug(form.toString());     // prints the form data to the console using the CreateTalentForm Bean form

        // validate the talent doesn't exist in the db, but also check if it's a create
        // when doing a manual check in the controller, we want this before the binding result.hasErrors check so that it will fall into that block of code
        // if this is a create:
        if(form.getId() == null) {
            HashSet<Talent> talents = talentDAO.findByNameIgnoreCase(form.getName());

            if (talents.size()>0) {
                bindingResult.rejectValue("name", "name", "This talent name is already in use. Manual check.");
            }
        }

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.debug("Validation error : " + ((FieldError) error).getField() + " = " + error.getDefaultMessage());
            }
            response.addObject("bindingResult", bindingResult); // error has occurred;, use on jsp page to show user the errors
            response.setViewName("talent/create");
            response.addObject("form", form);
            return response;

        } else {
            Talent talent = talentService.createTalent(form);
            // redirecting to the detail page, but usually you'd go to fully populated EDIT page, take id on url and use it to populate all the fields before rendering
            //response.setViewName("redirect:/talent/edit?id=" + form.getId());

            response.setViewName("redirect:/talent/list");      // this is a URL, NOT a view name, like an override to setViewName behavior (use an URL instead of a JSP file location)
            return response;
        }
    }

    @RequestMapping(value = "/edit", method = {RequestMethod.GET})
    public ModelAndView edit(@RequestParam  (required = false) Integer id) {

        // by setting required = false on the incoming parameter we allow
        ModelAndView response = new ModelAndView("talent/create");

        // load the talent from the database and set the form bean with all the talent values
        // this is because the form bean is on the JSP page, and we need to pre-populate the form with the talent data
        if ( id != null) {
            // we only do this code if we found the talent in the db
            Talent talent = talentDAO.findById(id);
            if (talent != null) {
                CreateTalentFormBean form = new CreateTalentFormBean();
                form.setId(talent.getId());
                form.setName(talent.getName());
                form.setDescription(talent.getDescription());
                form.setUrlSmallPhoto(talent.getUrlSmallPhoto());
                form.setUrlLargePhoto(talent.getUrlLargePhoto());
                form.setDateCreated(talent.getDateCreated());
                form.setDateUpdated(talent.getDateUpdated());
                form.setLastUpdatedId(talent.getLastUpdatedId());
                // if I want to show the dates and last updated id, add the lines here
                response.addObject("form", form);
            }
        }
        return response;
    }

     // listens on url: localhost:8080/talent/search
    @GetMapping("/search")
    public ModelAndView search(@RequestParam(required = false) String search) {

        ModelAndView response = new ModelAndView("talent/search");
        log.debug("The user searched for the term: " + search);

        // Add the user input back to the model so that we can display the search term in the input field
        response.addObject("searchKey", search);

        HashSet<Talent> talents = talentDAO.findByNameIgnoreCase(search);
        response.addObject("talentsKey", talents);

        return response;
    }
}
