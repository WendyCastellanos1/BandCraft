package com.baffintech.bandcraft.service;

import com.baffintech.bandcraft.database.entity.Band;
import com.baffintech.bandcraft.database.dao.BandDAO;
import com.baffintech.bandcraft.form.CreateBandFormBean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BandService {

    @Autowired
    private BandDAO bandDAO;

    //method to create a new band
//    public Member createBand(CreateBandFormBean form) {
//
//            // TODO
//    }








}
