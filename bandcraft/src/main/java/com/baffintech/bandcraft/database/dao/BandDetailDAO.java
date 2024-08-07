package com.baffintech.bandcraft.database.dao;


import com.baffintech.bandcraft.database.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface BandDetailDAO extends JpaRepository<BandDetail, Long> {

    BandDetail findById(long id);

    List<BandDetail> findByBandId(Integer bandId);      // TODO @Query



}
