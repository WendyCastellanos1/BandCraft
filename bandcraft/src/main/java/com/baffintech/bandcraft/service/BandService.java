package com.baffintech.bandcraft.service;

import com.baffintech.bandcraft.database.entity.Band;
import com.baffintech.bandcraft.database.dao.BandDAO;
import com.baffintech.bandcraft.database.entity.User;
import com.baffintech.bandcraft.form.CreateBandFormBean;

import com.baffintech.bandcraft.security.AuthenticatedUserUtilities;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.time.Instant;

@Slf4j
@Component
public class BandService {

    @Autowired
    private BandDAO bandDAO;

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;


    // method to create or edit a band record, return an updated object after save
    public Band createBand (@Valid CreateBandFormBean form, BindingResult bindingResult) {

        log.debug(form.toString());                     // write form data to the console
        Band band = bandDAO.findById(form.getId());     // make the object to load up

        Boolean isCreate = false;
        if (band == null) {                             // not found in  db, so this a create
            isCreate = true;
            band = new Band();
        }
        // get data from the form on the web page
        band.setIsSingleUse(form.getIsSingleUse());
        band.setLeadMemberId(form.getLeadMemberId());
        // TODO the rest


        if (isCreate){
            band.setDateCreated(Instant.now());          // set the *created* "timestamp"
            Byte isActiveFlag = 1;                       // default to active for now               // TODO later, admin will actively use this
            band.setIsActive(isActiveFlag);
        } else {
            band.setDateUpdated(Instant.now());          // set the *updated* timestamp;
        }

        // for both create/edit: get *logged-in user* (person creating or editing this record)       // TODO is the original available by reference?
        User user = authenticatedUserUtilities.getCurrentUser();
        log.debug(user.toString());
        band.setLastUpdatedId(user.getId());

        //  *save* to the db... auto-increment gives new id
        // basically returns the same object ...after it's been inserted into the db
        band = bandDAO.save(band);      // the new Id is available in the return from the save method.

        return band;
    }
}
