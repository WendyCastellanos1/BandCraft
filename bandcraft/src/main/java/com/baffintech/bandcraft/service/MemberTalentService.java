package com.baffintech.bandcraft.service;

import com.baffintech.bandcraft.database.entity.MemberTalent;
import com.baffintech.bandcraft.database.dao.MemberTalentDAO;
import com.baffintech.bandcraft.dto.TalentWithMemberStatusDTO;
import com.baffintech.bandcraft.form.CreateMemberTalentFormBean;

import com.baffintech.bandcraft.database.entity.Member;
import com.baffintech.bandcraft.database.dao.MemberDAO;

import com.baffintech.bandcraft.database.entity.Talent;
import com.baffintech.bandcraft.database.dao.TalentDAO;

import com.baffintech.bandcraft.database.entity.User;
import com.baffintech.bandcraft.security.AuthenticatedUserUtilities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MemberTalentService {

    @Autowired
    private MemberTalentDAO memberTalentDAO;

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private TalentDAO talentDAO;

    @Autowired
    private AuthenticatedUserUtilities authenticatedUserUtilities;

    //method to create a new MemberTalent, unless one already exists
    public MemberTalent createMemberTalent(Integer memberId, Integer talentId) {

        User user = authenticatedUserUtilities.getCurrentUser();

        MemberTalent memberTalent = memberTalentDAO.findByMemberIdAndTalentId(memberId, talentId);

        boolean isCreate = false;
        if (memberTalent == null) {                                                                    // this means person was not found in the members table, so we are going to consider this a create
            isCreate = true;
            memberTalent = new MemberTalent();
        }

        memberTalent.setMember(memberDAO.findById(memberId));
        memberTalent.setTalent(talentDAO.findById(talentId));

        if (isCreate) {                                                                               // TODO  I am defaulting to *active* on create, but admin can channge this when members say they're taking a break or quitting
            memberTalent.setDateCreated(Instant.now());                                               // set the *created* "timestamp"
        } else {
            memberTalent.setDateUpdated(Instant.now());                                               // set the *updated* timestamp;
        }

        log.debug(user.toString());
        memberTalent.setLastUpdatedId(user.getId());                                                   // current person (user) creating or editing this member record is *logged-in user*

        // when we save to the db, it will auto-increment to give us a new Id
        // the new Id is available in the return from the save method.
        // basically returns the same object ...after it's been inserted into the db
        memberTalent = memberTalentDAO.save(memberTalent); //want this bc has next Id number in it

        log.debug("memberTalent created:" + memberTalent.toString());
        return memberTalent;

        }

    public List<TalentWithMemberStatusDTO> buildCustomTalentListForFormByMember(Integer memberId) {

       // make the "custom" talent list (DTO) to pass to the view
        List<TalentWithMemberStatusDTO> talentsDTO = new ArrayList<>();

        // make a "generic" talent list using the talent entity and DAO
        List<Talent> talents = talentDAO.findAll();

        // loop to  map the data from the "pure talent" to the "custom DTO talent"
        for  (Talent talent : talents){
            // map the actual talent data to the DTO fields
            TalentWithMemberStatusDTO talentDTO = new TalentWithMemberStatusDTO();
            talentDTO.setId(talent.getId());
            talentDTO.setName(talent.getName());
            talentDTO.setDescription(talent.getDescription());

            //find out if this "pure talent" is already mapped to "this" member
            MemberTalent memberTalent = memberTalentDAO.findByMemberIdAndTalentId(memberId, talent.getId());
            if (memberTalent == null) {
                // the "pure talent" is NOT mapped to this member, so we'll set the flag so jstl can sub in message "available"
                talentDTO.setIsMapped(false);
            }else {
                // the "pure talent" is ALREADY mapped to this member, so we'll mark it as "already selected"
                talentDTO.setIsMapped(true);
            }

            // add the "custom talent" to its "custom list (DTO)"
            talentsDTO.add(talentDTO);
        }
        return talentsDTO;
    }
}
