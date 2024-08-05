package com.baffintech.bandcraft.service;

import com.baffintech.bandcraft.database.entity.Talent;
import com.baffintech.bandcraft.database.dao.TalentDAO;
import com.baffintech.bandcraft.form.CreateTalentFormBean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
public class TalentService {

    @Autowired
    private TalentDAO talentDAO;

    // method to create or edit a talent record, return an updated object
    public Talent createTalent(CreateTalentFormBean form) {

        // write form data to the console
        log.debug(form.toString());

        // make the object to load up
        Talent talent = talentDAO.findById(form.getId());

        Boolean isCreate = false;
        if (talent == null) {
            // this means it was not found in the db so we are going to consider this a create
            isCreate = true;
            talent = new Talent();
        }
        // get data from the form on the web page
        talent.setName(form.getName());
        talent.setDescription(form.getDescription());
        talent.setUrlSmallPhoto(form.getUrlSmallPhoto());
        talent.setUrlLargePhoto(form.getUrlLargePhoto());
        if (isCreate){
            talent.setDateCreated(Instant.now());
        } else {
            talent.setDateUpdated(Instant.now());
            // talent.setDateUpdateId(......);  TODO
        }

        Byte isActiveFlag = 1;              // TODO later, give admin control over this flag
        talent.setIsActive(isActiveFlag);

        // when we save to the db, it will auto-increment to give us a new id
        // the new Id is available in the return from the save method.
        // basically returns the same object ...after it's been inserted into the db
        talent = talentDAO.save(talent); //want this bc has next Id number in it

        return talent;
    }
}
