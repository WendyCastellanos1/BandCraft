package com.baffintech.bandcraft.service;

import com.baffintech.bandcraft.database.dao.UserRoleDAO;
import com.baffintech.bandcraft.database.entity.Member;
import com.baffintech.bandcraft.database.dao.MemberDAO;
import com.baffintech.bandcraft.database.entity.User;
import com.baffintech.bandcraft.database.entity.UserRole;
import com.baffintech.bandcraft.form.CreateMemberFormBean;

import com.baffintech.bandcraft.security.AuthenticatedUserUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static org.apache.taglibs.standard.functions.Functions.trim;

@Slf4j
@Component
public class MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;

    // method to create or edit a member in the db, return an updated object
    public Member createMember(CreateMemberFormBean form) {

        log.debug(form.toString());                                                     // write form data to the console
        // TODO make sure the member is not already in the table!

        User user = authenticatedUserUtilities.getCurrentUser();                        //TODO if the admin is editing someone else's record....
        Member member = memberDAO.findById(form.getId());

        Boolean isCreate = false;
        if (member == null) {                                                           // this means person was not found in the members table, so we are going to consider this a create
            isCreate = true;
            member = new Member();
            member.setUser(user);                                                       // for create, the talented member is the one filling out the form
        }

        // get data from the form on the web page to load up a member instance
        member.setFirstName(form.getFirstName());
        member.setLastName(form.getLastName());
        member.setNickname(form.getNickname());
        member.setGender(form.getGender());
        member.setGenderComment(form.getGenderComment());
        member.setGeneration(form.getGeneration());
        member.setPhoneCell(trim(form.getPhoneCell()));
        member.setPhoneAlt(trim(form.getPhoneAlt()));
        member.setEmailAlt(form.getEmailAlt());
        member.setBio(trim(form.getBio()));
        member.setSpeaksPortuguese(form.isSpeaksPortuguese());
        member.setSpeaksSpanish(form.isSpeaksSpanish());
        // member.setProfilePhoto(form.getProfilePhoto());                                          // TODO save path name after the file upload
        member.setProfilePhoto("/images/test_photo.jpg");
        member.setSocialMediaUrl(form.getSocialMediaUrl());

        if (isCreate) {                                                                               // TODO  I am defaulting to *active* on create, but admin can channge this when members say they're taking a break or quitting
            member.setDateCreated(Instant.now());                                                    // set the *created* "timestamp"
        } else {
            member.setDateUpdated(Instant.now());                                                     // set the *updated* timestamp;
        }

        log.debug(user.toString());
        member.setLastUpdatedId(user.getId());                                                         // current person (user) creating or editing this member record is *logged-in user*

        member = memberDAO.save(member); //want this bc has next Id number in it

//        // set new role (works, but not want I really want)
//        Boolean result = userRoleService.setNewUserRole(user.getId(), "MEMBER");
//
//        // delete USER role to ensure MEMBER authority in the app TODO does NOT work (this query works in MYSQL workbench, though)
//        Integer userId = user.getId();  //redundant , but testing something...
//        userRoleDAO.deleteRoleByUserId(userId, "USER");



        log.debug("member created after the edit, before returning to the createSubmit:" + member.toString());
        return member;
    }

    public String generationOptionsBuild(String generation) {                                     // note: these generations are hard-code bc of project requirement to limit tables

        String[] generationOptions = new String[7];                                                  // TODO replace with the ENUM I created
        String optionList = "";

        //put the select options in an array
        generationOptions[0] = "<option> Select one...</option>";

        if (generation.equals("a")) {
            generationOptions[1] = " <option selected value='a'>Gen Alpha</option>";
        } else {
            generationOptions[1] = " <option  value='a'>Gen Alpha</option>";
        }

        if (generation.equals("z")) {
            generationOptions[2] = " <option selected value='z'>Gen Z</option>";
        } else {
            generationOptions[2] = " <option value='z'>Gen Z</option>";
        }

        if (generation.equals("m")) {
            generationOptions[3] = " <option selected value='m'>Millenials</option>";
        } else {
            generationOptions[3] = " <option value='m'>Millenials</option>";
        }

        if (generation.equals("x")) {
            generationOptions[4] = " <option selected value='m'>Gen X</option>";
        } else {
            generationOptions[4] = " <option value='x'>Gen X</option>";
        }

        if (generation.equals("b")) {
            generationOptions[5] = " <option selected value='b'>Baby Boomers</option>";
        } else {
            generationOptions[5] = " <option value='b'>Baby Boomers</option>";
        }

        if (generation.equals("s")) {
            generationOptions[6] = " <option selected value='s'>Silent Generation</option>";
        } else {
            generationOptions[6] = " <option value='s'>Silent Generation</option>";
        }

        // loop to set up the option list in order to be inserted into the HTML later
        for (int x = 0; x < 7; x++) {
            optionList = optionList + generationOptions[x] + "/n";
        }

        return optionList;
    }
}
//    public genderRadioGroupBuild(String gender) {
//
//        String[] genderChunks= new String[3];
//        String genderRadioGroup = "";
//
//        if (gender.equals("m")) {
//            genderChunks[0] = "<input class=         \"form-check-input\"";
//                                    type=   "        \"radio\"    "
//                                    id=     "        \"maleId\"  "
//                                    value=  "        \"m\"       "
//                                    name=   "        \"gender\"  "
//                                + "checked>";
//
//        } else {
//            genderChunks[0] = "as";
//        }
//
//        if (gender.equals("f")) {
//            genderChunks[1] = "as";
//        } else {
//            genderChunks[1] = "as";
//        }
//
//        if (gender.equals("o")) {
//            genderChunks[2] = "asdf";
//        } else {
//            genderChunks[2] = "asdf";
//        }
//
//        // loop throught the HTML chunks to form code for the gender radio group, with correct choice checked on rendering
//        for (int k=0; k < genderChunks.length; k++) {
//            genderRadioGroup = genderRadioGroup + genderChunks[k] + "/n";
//        }
//
//        return genderRadioGroup;
//    }
//

