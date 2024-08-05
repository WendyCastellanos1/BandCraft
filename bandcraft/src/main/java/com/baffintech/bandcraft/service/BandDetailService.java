package com.baffintech.bandcraft.service;

import com.baffintech.bandcraft.database.entity.BandDetail;
import com.baffintech.bandcraft.database.dao.BandDetailDAO;
import com.baffintech.bandcraft.form.CreateBandDetailFormBean;

import com.baffintech.bandcraft.database.dao.MemberTalentDAO;

import com.baffintech.bandcraft.database.entity.BandDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class BandDetailService {

    @Autowired
    private BandDetailDAO bandDetailDAO;

    @Autowired
    private MemberTalentDAO memberTalentDAO;

//    public BandDetail createBandDetail(CreateBandDetailFormBean form) {
//        BandDetail bandDetail = bandDetailDAO.findById(form.getId());
//        if (bandDetail == null) {
//            bandDetail = new BandDetail();
//        }......................................................
//    }



}
