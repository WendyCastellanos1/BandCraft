package com.baffintech.bandcraft.service;

import com.baffintech.bandcraft.database.entity.MemberTalent;
import com.baffintech.bandcraft.database.dao.MemberTalentDAO;
import com.baffintech.bandcraft.form.CreateMemberFormBean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MemberTalentService {

    @Autowired
    private MemberTalentDAO memberTalentDAO;

    //method to create a new MemberTalent
//    public Member createMemberTalent(CreateMemberTalentFormBean form) {
//
//            // TODO
//    }


}
