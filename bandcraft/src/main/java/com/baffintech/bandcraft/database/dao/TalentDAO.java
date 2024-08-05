package com.baffintech.bandcraft.database.dao;


import com.baffintech.bandcraft.database.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface TalentDAO extends JpaRepository<Talent, Long> {

    Talent findById(Integer id);
    Talent findByIdAndIsActive(Integer id, Byte isActive);

    Talent findByName(String name);
    Talent findByNameAndIsActive(String name, Byte isActive);

    List<Talent> findByIsActive(Byte isActive);

}