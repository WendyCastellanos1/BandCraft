package com.baffintech.bandcraft.database.dao;


import com.baffintech.bandcraft.database.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface BandDetailDAO extends JpaRepository<BandDetail, Long> {

    BandDetail findById(long id);

    List<BandDetail> findByBandId(Integer bandId);

    @Query(value = " select * from band_details where band_id = :bandId and member_talent_id = :memberTalentId",
            nativeQuery = true)
    BandDetail isMemberTalentInBand(Integer bandId, Integer memberTalentId);



}
