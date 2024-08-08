package com.baffintech.bandcraft.service;

import com.baffintech.bandcraft.database.entity.Member;
import com.baffintech.bandcraft.database.dao.MemberDAO;
import com.baffintech.bandcraft.database.entity.User;
import com.baffintech.bandcraft.form.CreateMemberFormBean;

import com.baffintech.bandcraft.security.AuthenticatedUserUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
public class MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;


    //method to create or edit a member, return an updated object
    public Member createMember(CreateMemberFormBean form) {

        // write form data to the console
        log.debug(form.toString());

        // make the object to load up
        User user = authenticatedUserUtilities.getCurrentUser();
        Member member = memberDAO.findByUser(user);

        Boolean isCreate = false;
        if (member == null) {
            // this means person was not found in the members table, so we are going to consider this a create
            isCreate = true;
            member = new Member();
        }

        member.setUser(user);

        // get data from the form on the web page
        member.setFirstName(form.getFirstName());
        member.setLastName(form.getLastName());
        member.setNickname(form.getNickname());
        member.setGender(form.getGender());
        member.setGenderComment(form.getGenderComment());
        member.setGeneration(form.getGeneration());
        member.setPhoneCell(form.getPhoneCell());
        member.setPhoneAlt(form.getPhoneAlt());
        member.setEmailAlt(form.getEmailAlt());
        member.setBio(form.getBio());
        member.setSpeaksPortuguese(form.getSpeaksPortuguese());
        member.setSpeaksSpanish(form.getSpeaksSpanish());
        // member.setProfilePhoto(form.getProfilePhoto());  // Not doing this one bc it was a file upload
        member.setSocialMediaUrl(form.getSocialMediaUrl());


        if (isCreate){                                                                               // TODO  I am defaulting to *active* on create, but admin can channge this when members say they're taking a break or quitting
            // set the *created* "timestamp"
            member.setDateCreated(Instant.now());
            // because "add new," make active as default (for now)                                   // TODO later, admin will actively use this
            Byte isActiveFlag = 1;
            member.setIsActive(isActiveFlag);

        } else {
            // set the *updated* timestamp;
            member.setDateUpdated(Instant.now());
        }

        // *logged in user* is stored as person (user) creating or editing this record             // TODO is the original available by reference?
        log.debug(user.toString());
        member.setLastUpdatedId(user.getId());


        // when we save to the db, it will auto-increment to give us a new id
        // the new Id is available in the return from the save method.
        // basically returns the same object ...after it's been inserted into the db
        member = memberDAO.save(member); //want this bc has next Id number in it

        return member;
    }
}

    // method to check if member isBanned


    // method to check if member isActive


   // method(s) to deal with gender


    // method(s) to test for generation

