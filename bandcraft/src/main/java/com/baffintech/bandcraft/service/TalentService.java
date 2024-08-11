package com.baffintech.bandcraft.service;

import com.baffintech.bandcraft.database.entity.Talent;
import com.baffintech.bandcraft.database.dao.TalentDAO;
import com.baffintech.bandcraft.form.CreateTalentFormBean;

import com.baffintech.bandcraft.database.entity.User;
import com.baffintech.bandcraft.security.AuthenticatedUserUtilities;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.time.Instant;

@Slf4j
@Component
public class TalentService {

    @Autowired
    private TalentDAO talentDAO;

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;

    // method to create or edit a talent record in the db, return an updated object
    public Talent createTalent(CreateTalentFormBean form) {

        log.debug(form.toString());                                                        // write form data to the console
        Talent talent = talentDAO.findById(form.getId());                                  // create the talent instance to load up

        Boolean isCreate = false;
        if (talent == null) {                                                               // not found in the db, so this is a *create*
            isCreate = true;
            talent = new Talent();
        }

        // get data from the form on the web page
        talent.setName(form.getName());
        talent.setDescription(form.getDescription());
        talent.setUrlSmallPhoto(form.getUrlSmallPhoto());
        talent.setUrlLargePhoto(form.getUrlLargePhoto());

        if (isCreate){
            talent.setDateCreated(Instant.now());                                             // set the *created* "timestamp"
            Byte isActiveFlag = 1;                                                            // bc "add new," make active for now    // TODO later, admin will actively use this
            talent.setIsActive(isActiveFlag);
        } else {
            talent.setDateUpdated(Instant.now());                                             // set the *updated* timestamp;
        }

        // get logged in user to store as person making creating or editing this record
        User user = authenticatedUserUtilities.getCurrentUser();
        log.debug(user.toString());
        talent.setLastUpdatedId(user.getId());

        // when we *save* to the db, it will auto-increment to give us a new id
        // the new Id is available in the return from the save method.
        // basically returns the same object ...after it's been inserted into the db
        talent = talentDAO.save(talent); // want this bc has next Id number in it

        return talent;
    }
}
