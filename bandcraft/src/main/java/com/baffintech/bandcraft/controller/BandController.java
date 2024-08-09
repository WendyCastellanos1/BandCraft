package com.baffintech.bandcraft.controller;

import com.baffintech.bandcraft.database.dao.BandDAO;
import com.baffintech.bandcraft.database.entity.Band;
import com.baffintech.bandcraft.database.entity.Talent;
import com.baffintech.bandcraft.form.CreateBandFormBean;
import com.baffintech.bandcraft.form.CreateTalentFormBean;
import com.baffintech.bandcraft.service.BandService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/band")    // part of URL before the jsp page
public class BandController {

    @Autowired
    private BandDAO bandDAO;

    @Autowired
    private BandService bandService;

    @GetMapping("/create")
    public ModelAndView createBand(@RequestParam (required=false) Integer id) {

        ModelAndView response = new ModelAndView("band/create");
        return response;
    }


    @GetMapping("/createSubmit")
    public ModelAndView createSubmit(@Valid CreateBandFormBean form, BindingResult bindingResult) {

        ModelAndView response = new ModelAndView("band/create");
        response.addObject("form", form);

        log.info(form.toString());

        if (bindingResult.hasErrors()) {
            response.addObject("bindingResult", bindingResult);
            return response;
        }

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.debug("Validation error : " + ((FieldError) error).getField() + " = " + error.getDefaultMessage());
            }
            response.addObject("bindingResult", bindingResult);                                         // error has occurred;, use on jsp page to show user the errors
            //response.setViewName("band/create");      TODO - decide where you want it to go, back to where it was?
            response.addObject("form", form);
            return response;

        } else {
            Band band = bandService.createBand(form, bindingResult);
            // if you want to redirect to another page      //response.setView("redirect:/someohterurl?id=" + band.getId());

            return response;
        }
    }

    @RequestMapping(value = "/edit", method = {RequestMethod.GET})
    public ModelAndView edit(@RequestParam  (required = false) Integer id) {

        ModelAndView response = new ModelAndView("band/create");

        // load the band from the database and set the form bean with all the band values
        // this is because the form bean is on the JSP page, and we need to pre-populate the form with the band data
        if ( id != null) {
            // we only do this code if we found the talent in the db
            Band band = bandDAO.findById(id);
            if (band != null) {
                CreateBandFormBean form = new CreateBandFormBean();
                form.setId(band.getId());
                form.setIsActive(band.getIsActive());
                form.setIsSingleUse(band.getIsSingleUse());
                form.setLeadMemberId(band.getLeadMemberId());
                form.setEventId(band.getEventId());
                form.setDateCreated(band.getDateCreated());
                form.setDateUpdated(band.getDateUpdated());
                form.setLastUpdatedId(band.getLastUpdatedId());

                response.addObject("form", form);
            }
        }
        return response;
    }
}