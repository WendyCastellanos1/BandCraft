package com.baffintech.bandcraft.service;

import com.baffintech.bandcraft.database.entity.Talent;
import com.baffintech.bandcraft.database.dao.TalentDAO;
import com.baffintech.bandcraft.form.CreateTalentFormBean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TalentService {

    @Autowired
    private TalentDAO talentDAO;

    //method to create a new MemberTalent
//    public Member createMemberTalent(CreateMemberTalentFormBean form) {
//
//            // TODO
//    }

}
