package com.baffintech.bandcraft.service;

import com.baffintech.bandcraft.database.entity.Member;
import com.baffintech.bandcraft.database.dao.MemberDAO;
import com.baffintech.bandcraft.form.CreateMemberFormBean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MemberService {

    @Autowired
    private MemberDAO memberDAO;

    //method to create a new member
//    public Member createMember(CreateMemberFormBean form) {
//
//            // TODO
//    }








    // method to check if member isBanned


    // method to check if member isActive


   // method(s) to deal with gender


    // method(s) to test for generation




}
