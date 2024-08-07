package com.baffintech.bandcraft.database.dao;

import com.baffintech.bandcraft.database.entity.*;
import org.springframework.data.jpa.repository.*;

public interface BandDAO extends JpaRepository<Band, Long> {

    Band findById(Integer id);

    // Band findByleader(Integer memberId);        // TODO  native query



}