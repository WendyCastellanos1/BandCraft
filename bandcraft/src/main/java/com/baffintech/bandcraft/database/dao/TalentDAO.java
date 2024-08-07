package com.baffintech.bandcraft.database.dao;

import com.baffintech.bandcraft.database.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface TalentDAO extends JpaRepository<Talent, Long> {

    Talent findById(Integer id);
    Talent findByIdAndIsActive(Integer id, Byte isActive);          // TODO test

    Talent findByName(String name);
    Talent findByNameIgnoreCase(String name);
    //Talent findByNameAndIsActive(String name, Byte isActive);     // TODO   test

    List<Talent> findByIsActive(Byte isActive);                     // TODO  test


}