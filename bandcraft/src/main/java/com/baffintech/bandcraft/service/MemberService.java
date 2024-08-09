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

    // method to create or edit a member in the db, return an updated object
    public Member createMember(CreateMemberFormBean form) {

        log.debug(form.toString());                                                     // write form data to the console

        User user = authenticatedUserUtilities.getCurrentUser();
        Member member = memberDAO.findByUser(user);

        Boolean isCreate = false;
        if (member == null) {                                                           // this means person was not found in the members table, so we are going to consider this a create
            isCreate = true;
            member = new Member();
        }

        member.setUser(user);
        // member.setUserId(user.getId());                                                      // TODO  can I do this?

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
        // member.setProfilePhoto(form.getProfilePhoto());                                          // TODO save path name after the file upload
        member.setProfilePhoto("/images/test_photo.jpg");
        member.setSocialMediaUrl(form.getSocialMediaUrl());

        if (isCreate){                                                                               // TODO  I am defaulting to *active* on create, but admin can channge this when members say they're taking a break or quitting
            member.setDateCreated(Instant.now());                                                    // set the *created* "timestamp"
            Byte isActiveFlag = 1;                                                                   // because "add new," make active as default (for now)                                   // TODO later, admin will actively use this
            member.setIsActive(isActiveFlag);
        } else {
            member.setDateUpdated(Instant.now());                                                     // set the *updated* timestamp;
        }

        log.debug(user.toString());
        member.setLastUpdatedId(user.getId());                                                         // person (user) creating or editing this member record is *logged-in user*

        // when we save to the db, it will auto-increment to give us a new id
        // the new Id is available in the return from the save method.
        // basically returns the same object ...after it's been inserted into the db
        member = memberDAO.save(member); //want this bc has next Id number in it
        return member;
    }
}

